package com.example.yemekuygulamasi.ui.fragment

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.data.entitiy.Siparis
import com.example.yemekuygulamasi.data.repo.AnimasyonRepository
import com.example.yemekuygulamasi.databinding.FragmentSepetOnaylaBinding
import com.example.yemekuygulamasi.ui.viewmodel.SepetOnaylaViewModel
import com.example.yemekuygulamasi.ui.viewmodel.SepetViewmodel
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class SepetOnaylaFragment : Fragment() {
    private lateinit var binding:FragmentSepetOnaylaBinding
    private lateinit var viewmodel : SepetOnaylaViewModel
    private lateinit var  database:FirebaseDatabase
    private lateinit var viewmodelSepet: SepetViewmodel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var database =FirebaseDatabase.getInstance().reference


        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_sepet_onayla,container,false)

        val shape = resources.getDrawable(R.drawable.adress_back) as GradientDrawable
        shape.setStroke(2, Color.BLACK)
        val bundle:SepetOnaylaFragmentArgs by navArgs()
        val toplam = bundle.sepetonay
        binding.txtToplamFiyat.text=toplam.toString()
        binding.txtToplam.text=toplam.toString()



            binding.buttonSepetiOnayla.setOnClickListener {
                if(!binding.editTextTextPersonName2.text.isEmpty()){
                    if(binding.radioButton2.isChecked){
                        Navigation.findNavController(it).navigate(R.id.toOdeme)
                    }else if (binding.radioButton.isChecked || binding.radioButton3.isChecked){
                        Toast.makeText(requireContext(),"Kontrol Ediliyor",Toast.LENGTH_SHORT).show()

                        val handler = Handler()
                        handler.postDelayed({
                            Toast.makeText(requireContext(),"Sipariş Başarıyla Oluşturuldu! Afiyet olsun :)",Toast.LENGTH_SHORT).show()
                            AnimasyonRepository.animasyon(requireContext(),R.layout.custom_alert_dialog)
                            val id = UUID.randomUUID().toString()

                                val tarih = LocalDateTime.now()
                                val formatter = DateTimeFormatter.ofPattern("HH-ss  dd-MM-yy")
                                val formatliTarih = tarih.format(formatter)


                            viewmodelSepet.sepetler.observe(viewLifecycleOwner){
                                database.child(id.toString()).setValue(Siparis(id,formatliTarih.toString(),it))
                            }


                            val handler2 =Handler()
                            handler2.postDelayed({
                                sepetiBosalt()
                                Toast.makeText(requireContext(),"Ödeme Başarılı Afiyet olsun :)",Toast.LENGTH_SHORT).show()
                                Navigation.findNavController(it).navigate(R.id.toAnasayfaFromSepetOnayla)

                            },2200)
                        }, 4000)


                    }
                    if(binding.radioButton2.isChecked || binding.radioButton.isChecked || binding.radioButton3.isChecked){

                    }else{
                        Toast.makeText(requireContext(),"Lütfen ödeme yöntemini seçiniz",Toast.LENGTH_LONG).show()
                    }

                }else{
                    Toast.makeText(requireContext(),"Lütfen Adres doldurunuz",Toast.LENGTH_SHORT).show()
                }

            }


            return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewmodel : SepetOnaylaViewModel by viewModels()
        viewmodel=tempViewmodel

        val tempviewmodel2 : SepetViewmodel by viewModels()
        viewmodelSepet = tempviewmodel2
    }
    fun sepetiBosalt(){
        viewmodel.sepetiBosalt()
    }



}