package com.example.yemekuygulamasi.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract.SimAccount
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.SignInActivity
import com.example.yemekuygulamasi.data.entitiy.Sepet
import com.example.yemekuygulamasi.data.entitiy.Siparis
import com.example.yemekuygulamasi.data.entitiy.SiparisCek
import com.example.yemekuygulamasi.data.repo.AnimasyonRepository
import com.example.yemekuygulamasi.databinding.FragmentProfilBinding
import com.example.yemekuygulamasi.ui.LoadingDialog
import com.example.yemekuygulamasi.ui.adapter.ProfilAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase


class ProfilFragment : Fragment() {
   private lateinit var binding : FragmentProfilBinding
    var  list =ArrayList<Sepet>()
    var listSiparis = ArrayList<Siparis>()
    var id:String =""
    private lateinit var auth : FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilBinding.inflate(inflater,container,false)

        auth = Firebase.auth
        var database = FirebaseDatabase.getInstance().reference
        val loading = LoadingDialog(requireActivity())
        loading.startLoading()


        var getData = object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listSiparis.clear()
            for (i in snapshot.children){
                val tarih=i.child("tarih").value.toString()
                val sepet=i.child("sepetListe").value
                 id =i.child("siparis_id").value.toString()
                val k1=Siparis(id,tarih, sepet as List<Sepet>)
                listSiparis.add(k1)

            }
                val handler2 = Handler()
                handler2.postDelayed(object : Runnable{
                    override fun run() {
                      loading.isDismiss()
                    }
                },1000)
                binding.txtEmailProfil.text = auth.currentUser!!.email
                val adapter =ProfilAdapter(requireContext(),listSiparis)
                binding.siparisAdapter = adapter
                adapter.notifyDataSetChanged()

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        database.addValueEventListener(getData)
        database.addListenerForSingleValueEvent(getData)

        binding.imageView6.setOnClickListener {
            logOut(auth)

        }
        //val adapter = ProfilAdapter(requireContext())
        return binding.root
    }

    fun logOut(auth : FirebaseAuth){
        auth.signOut()
        val builder = AlertDialog.Builder(requireContext())

        builder.setMessage("Eylemi gerçekleştirmek istediğinize emin misiniz?")

        builder.setPositiveButton("Evet") { dialog, which ->
            val intent = Intent(requireContext(),SignInActivity::class.java)
            startActivity(intent)
        }

        builder.setNegativeButton("Hayır") { dialog, which ->
            // Hayır'a tıklandığında yapılacak işlemler burada yer alacak
            // Örneğin, işlemi iptal etmek veya bir işlemi reddetmek gibi
        }

        val dialog = builder.create()
        dialog.show()

    }

}