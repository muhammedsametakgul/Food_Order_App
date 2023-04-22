package com.example.yemekuygulamasi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.databinding.FragmentDetayBinding
import com.example.yemekuygulamasi.ui.viewmodel.DetayViewmodel
import com.google.android.material.snackbar.Snackbar
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
        binding.textViewDetayAd.text=gelenYemek.yemek_adi.toString()

        viewModel.sayiVM.observe(viewLifecycleOwner){
            binding.adetSayisi=it
            var fiyat = gelenYemek.yemek_fiyat
            var sepetFiyat=(it.toString().toInt()) * fiyat
            binding.buttonSepet.text="Sepete Ekle        ${sepetFiyat} ₺"

            binding.imageViewClose.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.toAnasayfaFromDetay)
            }

                binding.buttonSepet.setOnClickListener {
                    val gelensayi = viewModel.yrepo.sepetListesi.value?.size
                    var gelenSayi2=0
                    if(gelensayi ==null){
                        gelenSayi2 = 0
                    }else{
                        gelenSayi2=gelensayi
                    }
                    var geciciAdet = 0
                    var sayac = 0

                    for (x in 0..gelenSayi2 - 1) {
                        if ( gelenYemek.yemek_adi == viewModel.yrepo.sepetListesi.value?.get(x)?.yemek_adi){

                            geciciAdet = viewModel.yrepo.sepetListesi.value?.get(x)?.yemek_siparis_adet.toString().toInt()

                            viewModel.silDetay(viewModel.yrepo.sepetListesi.value?.get(x)?.sepet_yemek_id.toString().toInt(),"samet")
                            sayac++

                        }
                    }
                    sepeteEkle(gelenYemek.yemek_adi,gelenYemek.yemek_resim_adi,gelenYemek.yemek_fiyat,binding.textViewYemekAdet.text.toString().toInt()+geciciAdet,"samet")

                    if (sayac == 0){
                        Snackbar.make(it,"Sepete ${gelenYemek.yemek_adi}  Eklendi",Snackbar.LENGTH_SHORT).show()
                    }else{
                        Snackbar.make(it," Sepeteki ${gelenYemek.yemek_adi} Güncellendi.",Snackbar.LENGTH_SHORT).show()
                    }


            }

        }

        val url="http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        gorselGoster(url)
        binding.textViewDetayFiyat.text="${gelenYemek.yemek_fiyat.toString()} ₺"
        binding.buttonSepet.text="Sepete Ekle"
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel :DetayViewmodel by viewModels()
        viewModel=tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.sepetiYukle()
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