package com.example.yemekuygulamasi.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.data.entitiy.Sepet
import com.example.yemekuygulamasi.data.entitiy.Siparis
import com.example.yemekuygulamasi.data.repo.AnimasyonRepository
import com.example.yemekuygulamasi.databinding.FragmentSepetBinding
import com.example.yemekuygulamasi.ui.adapter.SepetAdapter
import com.example.yemekuygulamasi.ui.viewmodel.SepetViewmodel
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class SepetFragment : Fragment() {
    private lateinit var binding:FragmentSepetBinding
    private lateinit var viewmodel:SepetViewmodel
    var toplamFiyat = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sepet,container,false)
        binding.sepetFragment = this

        //Observe the sepetler list and shows data in interface as dynamically
        viewmodel.sepetler.observe(viewLifecycleOwner){
            val adapter = SepetAdapter(requireContext(),it,viewmodel,requireActivity())
            binding.sepetAdapter=adapter

            val liste = it
            val tumSepet = viewmodel.sepetler.value!!
            //Total basket price
            binding.textViewSepetToplam.text = sepetToplamFiyat(tumSepet,tumSepet.size).toString() + " ₺"
            val toplam =binding.textViewSepetToplam.text.toString()
            binding.buttonDevam.text="Devam - ${adapter.itemCount} adet ürün"
            //Pass data to BasketApprove
            binding.buttonDevam.setOnClickListener {
                if(liste.size ==0 || liste.size<1){
                    Toast.makeText(requireContext(),"Lütfen sepete en az 1 yiyecek ekleyiniz",Toast.LENGTH_SHORT).show()
                }else{
                    val gecis = SepetFragmentDirections.toSepetOnayla(toplam)
                    Navigation.findNavController(it).navigate(gecis)
                }

            }

        }
        //Clear all basket
        binding.imageView7.setOnClickListener {view->
            val snackbar = Snackbar.make(view, "Sepet Boşaltılsın mı?", Snackbar.LENGTH_LONG)
            snackbar.setAction("Evet") {
             sepetiBosalt()
             Toast.makeText(requireContext(),"Sepet Boşaltıldı",Toast.LENGTH_SHORT).show()
             Navigation.findNavController(view).navigate(R.id.toAnasayfaFromSepet)

            }
            snackbar.addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    if (event == DISMISS_EVENT_TIMEOUT || event == DISMISS_EVENT_SWIPE) {
                    }
                }
            })
            snackbar.show()


        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewmodel : SepetViewmodel by viewModels()
        viewmodel=tempViewmodel
    }

    override fun onResume() {
        super.onResume()
        viewmodel.sepetiGetir()
    }
    //Calculate total price by multiplying the price by the quantity
    fun sepetToplamFiyat(tumSepet: List<Sepet>, sepetsize:Int) : Int {
         toplamFiyat = 0

        for (i in 0..sepetsize-1) {
            toplamFiyat += (tumSepet.get(i).yemek_siparis_adet.toInt() * tumSepet.get(i).yemek_fiyat.toInt())
        }

        return toplamFiyat
    }

    //Clear basket func
    fun sepetiBosalt(){
        viewmodel.sepetiBosalt()
    }


}