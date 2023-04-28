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
        //While loading page, it shows loading
        val loading = LoadingDialog(requireActivity())
        loading.startLoading()

        //Get data from Firebase Realtime Database
        var getData = object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //before get, we clear list. Otherwise, each opening this page, it will override on list and will show more items than the list has as real
                listSiparis.clear()
                //Get data and put them into listSiparis to give as parameter to ProfilAdapter
            for (i in snapshot.children){
                val tarih=i.child("tarih").value.toString()
                val sepet=i.child("sepetListe").value
                 id =i.child("siparis_id").value.toString()
                val k1=Siparis(id,tarih, sepet as List<Sepet>)
                listSiparis.add(k1)

            }
                //Close the loading
                val handler2 = Handler()
                handler2.postDelayed(object : Runnable{
                    override fun run() {
                      loading.isDismiss()
                    }
                },400)
                //Write the email on the textview
                binding.txtEmailProfil.text = auth.currentUser!!.email
                val adapter =ProfilAdapter(requireContext(),listSiparis)
                binding.siparisAdapter = adapter
                adapter.notifyDataSetChanged()

            }
            override fun onCancelled(error: DatabaseError) {
            }

        }
        //Continue get data
        database.addValueEventListener(getData)
        database.addListenerForSingleValueEvent(getData)

        //Log out icon  clicked
        binding.imageView6.setOnClickListener {
            logOut(auth)
        }

        return binding.root
    }

    //Ask the user if she/he is sure to log out ,and if the answer is yes, it logs out.
    fun logOut(auth : FirebaseAuth){
        auth.signOut()
        val builder = AlertDialog.Builder(requireContext())

        builder.setMessage("Çıkış yapmak istediğinize emin misiniz?")

        builder.setPositiveButton("Evet") { dialog, which ->
            val intent = Intent(requireContext(),SignInActivity::class.java)
            startActivity(intent)
        }

        builder.setNegativeButton("Hayır") { dialog, which ->
        }

        val dialog = builder.create()
        dialog.show()

    }

}