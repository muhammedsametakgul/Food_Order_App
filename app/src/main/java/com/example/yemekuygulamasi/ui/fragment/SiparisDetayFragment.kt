package com.example.yemekuygulamasi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.data.entitiy.Sepet
import com.example.yemekuygulamasi.data.entitiy.Siparis
import com.example.yemekuygulamasi.databinding.FragmentSiparisDetayBinding
import com.example.yemekuygulamasi.ui.adapter.ProfilAdapter
import com.example.yemekuygulamasi.ui.adapter.SiparisDetayAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.annotations.SerializedName


class SiparisDetayFragment : Fragment() {
    private  lateinit var binding : FragmentSiparisDetayBinding
    var listSiparis =ArrayList<Sepet>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSiparisDetayBinding.inflate(inflater,container,false)
        var database = FirebaseDatabase.getInstance()
        val bundle:SiparisDetayFragmentArgs by navArgs()
        val id = bundle.siparis

        val reference = database.getReference("$id/sepetListe")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listSiparis.clear() // Önceki verileri silmek için listeyi temizleyin
                for (i in snapshot.children) {
                    val sepet_yemek_id = i.child("sepet_yemek_id").value.toString().toInt()
                    val yemek_adi = i.child("yemek_adi").value.toString()
                    val yemek_resim_adi = i.child("yemek_resim_adi").value.toString()
                    val fiyat = i.child("yemek_fiyat").value.toString().toInt()
                    val yemek_siparis_adet = i.child("yemek_siparis_adet").value.toString().toInt()
                    val kullanici_adi = i.child("kullanici_adi").value.toString()
                    val k1 = Sepet(sepet_yemek_id, yemek_adi, yemek_resim_adi, fiyat, yemek_siparis_adet, kullanici_adi)
                    listSiparis.add(k1)

                }

                val adapter = SiparisDetayAdapter(requireContext(),listSiparis,requireActivity())
                binding.siparisDetayAdater = adapter
                adapter.notifyDataSetChanged() // Veriler güncellendiğinde adapter'ı yenileyin
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Data read error: $error")
            }
        })


        //val adapter = ProfilAdapter(requireContext())

        return binding.root
    }


}