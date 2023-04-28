package com.example.yemekuygulamasi.ui.fragment

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.data.entitiy.Siparis
import com.example.yemekuygulamasi.data.repo.AnimasyonRepository
import com.example.yemekuygulamasi.databinding.FragmentOdemeBinding
import com.example.yemekuygulamasi.ui.viewmodel.OdemeViewModel
import com.example.yemekuygulamasi.ui.viewmodel.SepetOnaylaViewModel
import com.example.yemekuygulamasi.ui.viewmodel.SepetViewmodel
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class OdemeFragment : Fragment() {
    private lateinit var binding:FragmentOdemeBinding
    private lateinit var viewModel: OdemeViewModel
    private lateinit var viewModelSepet : SepetViewmodel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Create database object
        val database = FirebaseDatabase.getInstance().reference
        binding = FragmentOdemeBinding.inflate(inflater,container,false)

        binding.buttonOdemeTamamla.setOnClickListener {
            //Null Control
            if(binding.editTextTextPersonName.text.isEmpty()){
                Toast.makeText(requireContext(),"Lütfen AD SOYAD alanını doldurunuz",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if (binding.editTextNumber.text.isEmpty()){
                Toast.makeText(requireContext(),"Lütfen KART NUMARA alanını doldurunuz",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(binding.editTextAy.text.isEmpty()){
                Toast.makeText(requireContext(),"Lütfen AY alanını doldurunuz",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(binding.editTextYil.text.isEmpty()){
                Toast.makeText(requireContext(),"Lütfen YIL alanını doldurunuz",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(binding.editTextCvv.text.isEmpty()){
                Toast.makeText(requireContext(),"Lütfen CVV alanını doldurunuz",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                progressPay()
                Toast.makeText(requireContext(),"Cevap Bekleniyor",Toast.LENGTH_SHORT).show()
                //while above Toast message appear, handler makes a delay
                val handler = Handler()
                handler.postDelayed({
                    val id = UUID.randomUUID().toString()
                    val tarih = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("HH-ss  dd-MM-yy")
                    val formatliTarih = tarih.format(formatter)
                    //Add items which are in basket list to order history
                    viewModelSepet.sepetler.observe(viewLifecycleOwner){
                        database.child(id.toString()).setValue(Siparis(id,formatliTarih.toString(),it))
                    }
                    //Clear the basket
                    sepetiBosalt()
                    Toast.makeText(requireContext(),"Ödeme Başarılı Afiyet olsun :)",Toast.LENGTH_SHORT).show()
                    //Shows an animation on Dialog
                    AnimasyonRepository.animasyon(requireContext(),R.layout.custom_alert_dialog)
                    val handler2 =Handler()
                    handler2.postDelayed({
                        toAnimation(it)
                    },2200)
                }, 4000)
            }
        }

        return binding.root
    }


    //Make the imageview  which shows if the card is mastercard or visa  visible
    fun progressPay(){
        //When button clicked, place information which user entered below on card design
        binding.txtOdemeKartNo.text= binding.editTextNumber.text.toString()
        binding.textViewOdemeAd.text = binding.editTextTextPersonName.text.toString().toUpperCase()
        binding.txtOdemeTarih.text=binding.editTextAy.text.toString()+"/"+binding.editTextYil.text.toString()
        binding.textViewCVV.text="***"
        val first=binding.editTextNumber.text.substring(0,1)
        //Visa card starts with 4
        if(first=="4"){
            binding.imageViewCard.visibility=View.VISIBLE
            binding.imageViewCard.setImageResource(R.drawable.visaicon)
        }else if(first == "5"){
            //Mastercard card starts with 5
            binding.imageViewCard.visibility=View.VISIBLE
            binding.imageViewCard.setImageResource(R.drawable.mastercard)
        }

    }
    //After payment, it directs us to MainFragment/AnaSayfa
    fun toAnimation(view:View){
        Navigation.findNavController(view).navigate(R.id.toAnasayfaFromOdeme)
    }
    //Delete All items in basket( it will be used after payment is successful)
    fun sepetiBosalt(){
        viewModel.sepetiBosalt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //implemantation viewmodels
        val temp : OdemeViewModel by viewModels()
        viewModel= temp

        val temp2 : SepetViewmodel by viewModels()
        viewModelSepet =temp2
    }

}