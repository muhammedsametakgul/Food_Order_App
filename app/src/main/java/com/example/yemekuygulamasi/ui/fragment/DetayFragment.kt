package com.example.yemekuygulamasi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.databinding.FragmentDetayBinding
import com.example.yemekuygulamasi.ui.viewmodel.DetayViewmodel
import com.squareup.picasso.Picasso


class DetayFragment : Fragment() {
    private  lateinit var binding:FragmentDetayBinding
    private lateinit var viewModel: DetayViewmodel
    var sayi = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detay,container,false)
        binding.yemekDetayFragment = this
        val bundle:DetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek
        binding.yemekNesnesi  = gelenYemek
        viewModel.sayiVM.observe(viewLifecycleOwner){
            binding.adetSayisi=it
            var fiyat = gelenYemek.yemek_fiyat
            var sepetFiyat=(it.toString().toInt()) * fiyat
            binding.buttonSepet.text="Sepete Ekle        ${sepetFiyat} ₺"
        }

        val url="http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        gorselGoster(url)
        binding.textViewDetayFiyat.text="${gelenYemek.yemek_fiyat.toString()} ₺"
        binding.buttonSepet.text="Sepete Ekle"

        binding.buttonSepet.setOnClickListener {
            sepeteEkle(gelenYemek.yemek_adi,gelenYemek.yemek_resim_adi,gelenYemek.yemek_fiyat,1,"samet")
        }



        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel :DetayViewmodel by viewModels()
        viewModel=tempViewModel
    }
    fun sepeteEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
     viewModel.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }
    fun Nav(it:View){
        val gecis = DetayFragmentDirections.toSepet()
        Navigation.findNavController(it).navigate(gecis)
    }
    fun gorselGoster(url:String){
        Picasso.get().load(url).into(binding.imageViewDetayFoto)

    }
    fun arttir(){
        viewModel.arttir()

    }
    fun azalt(){
        viewModel.azalt()

    }

}