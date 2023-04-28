package com.example.yemekuygulamasi.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.data.entitiy.Favoriler
import com.example.yemekuygulamasi.databinding.FragmentAnasayfaBinding
import com.example.yemekuygulamasi.ui.BadgeBox
import com.example.yemekuygulamasi.ui.adapter.AnasayfaAdapter
import com.example.yemekuygulamasi.ui.viewmodel.AnasayfaViewmodel
import com.example.yemekuygulamasi.ui.viewmodel.FavorilerVMF
import com.example.yemekuygulamasi.ui.viewmodel.FavorilerViewModel


class AnasayfaFragment : Fragment(){
    private lateinit var binding:FragmentAnasayfaBinding
    private lateinit var viewmodel:AnasayfaViewmodel
    private lateinit var viewmodelFavoriler : FavorilerViewModel
    private lateinit var list: List<Favoriler>
    private lateinit var adapter : AnasayfaAdapter
    private lateinit var badgeBoxInterface: BadgeBox

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa, container, false)
        binding.anasayfaFragment=this
        binding.yemekTool=""
        binding.anasayfaFragment = this



        //badgeBoxInterface.onNumberReceived(5)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodelFavoriler.favorilerListesi.observe(viewLifecycleOwner){favori->
            viewmodel.yemeklerListesi.observe(viewLifecycleOwner){yemek->
                adapter =AnasayfaAdapter(requireContext(),yemek,viewmodelFavoriler,favori,viewmodel)
                binding.yemekAdapter=adapter

            }

        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : AnasayfaViewmodel by viewModels()
        viewmodel=tempViewModel

        val temp : FavorilerViewModel by viewModels() {
            FavorilerVMF(requireActivity().application)
        }
        viewmodelFavoriler = temp
    }


 /* override fun onResume() {
        super.onResume()
        viewmodel.yemekleriYukle()
    }*/

    fun artanFiyat(){
        viewmodel.artanFiyat()
    }
    fun azalanFiyat(){
        viewmodel.azalanFiyat()
    }

   /* override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BadgeBox) {
            badgeBoxInterface = context
        } else {
            throw RuntimeException("$context must implement MyInterface")
        }
    }*/



}