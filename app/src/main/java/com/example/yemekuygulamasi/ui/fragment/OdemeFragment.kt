package com.example.yemekuygulamasi.ui.fragment

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
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.databinding.FragmentOdemeBinding
import com.example.yemekuygulamasi.ui.viewmodel.OdemeViewModel
import com.example.yemekuygulamasi.ui.viewmodel.SepetOnaylaViewModel

class OdemeFragment : Fragment() {
    private lateinit var binding:FragmentOdemeBinding
    private lateinit var viewModel: OdemeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOdemeBinding.inflate(inflater,container,false)
        binding.buttonOdemeTamamla.setOnClickListener {
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
                val handler = Handler()
                handler.postDelayed({
                    sepetiBosalt()
                    Toast.makeText(requireContext(),"Ödeme Başarılı Afiyet olsun :)",Toast.LENGTH_SHORT).show()
                    toAnimation(it)
                }, 4000)
            }
        }

        return binding.root
    }



    fun progressPay(){
        binding.txtOdemeKartNo.text= binding.editTextNumber.text.toString()
        binding.textViewOdemeAd.text = binding.editTextTextPersonName.text.toString().toUpperCase()
        binding.txtOdemeTarih.text=binding.editTextAy.text.toString()+"/"+binding.editTextYil.text.toString()
        binding.textViewCVV.text="***"
        val first=binding.editTextNumber.text.substring(0,1)
        if(first=="4"){
            binding.imageViewCard.visibility=View.VISIBLE
            binding.imageViewCard.setImageResource(R.drawable.visaicon)
        }else if(first == "5"){
            binding.imageViewCard.visibility=View.VISIBLE
            binding.imageViewCard.setImageResource(R.drawable.mastercard)
        }

    }
    fun toAnimation(view:View){
        Navigation.findNavController(view).navigate(R.id.toAnimation1)
    }
    fun sepetiBosalt(){
        viewModel.sepetiBosalt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp : OdemeViewModel by viewModels()
        viewModel= temp
    }

}