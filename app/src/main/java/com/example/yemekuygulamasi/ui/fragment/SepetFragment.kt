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
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.data.entitiy.Sepet
import com.example.yemekuygulamasi.databinding.FragmentSepetBinding
import com.example.yemekuygulamasi.ui.adapter.SepetAdapter
import com.example.yemekuygulamasi.ui.viewmodel.SepetViewmodel
import com.google.android.material.snackbar.Snackbar


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


        viewmodel.sepetler.observe(viewLifecycleOwner){

            val adapter = SepetAdapter(requireContext(),it,viewmodel,requireActivity())
            binding.sepetAdapter=adapter
            Log.e("Size",adapter.itemCount.toString())

            val liste = it
            val tumSepet = viewmodel.sepetler.value!!
            binding.textViewSepetToplam.text = sepetToplamFiyat(tumSepet,tumSepet.size).toString() + " ₺"
            val toplam =binding.textViewSepetToplam.text.toString()
            binding.buttonDevam.text="Devam - ${adapter.itemCount} adet ürün"
            binding.buttonDevam.setOnClickListener {
                if(liste.size ==0 || liste.size<1){
                    Toast.makeText(requireContext(),"Lütfen sepete en az 1 yiyecek ekleyiniz",Toast.LENGTH_SHORT).show()
                }else{
                    val gecis = SepetFragmentDirections.toSepetOnayla(toplam)
                    Navigation.findNavController(it).navigate(gecis)
                }

            }

        }
        binding.imageView7.setOnClickListener {
            val snackbar = Snackbar.make(it, "Sepet Boşaltılsın mı?", Snackbar.LENGTH_LONG)
            snackbar.setAction("Evet") {
              sepetiBosalt()
            }
            snackbar.addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    if (event == DISMISS_EVENT_TIMEOUT || event == DISMISS_EVENT_SWIPE) {
                        // Hayır seçeneği tıklandığında yapılacak işlemler
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
    fun sepetToplamFiyat(tumSepet: List<Sepet>, sepetsize:Int) : Int {
         toplamFiyat = 0

        for (i in 0..sepetsize-1) {
            toplamFiyat += (tumSepet.get(i).yemek_siparis_adet.toInt() * tumSepet.get(i).yemek_fiyat.toInt())
        }

        return toplamFiyat
    }

    fun sepetiBosalt(){
        viewmodel.sepetiBosalt()
    }


}