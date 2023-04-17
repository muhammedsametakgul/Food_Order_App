package com.example.yemekuygulamasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.data.entitiy.Yemekler
import com.example.yemekuygulamasi.databinding.FragmentAnasayfaBinding
import com.example.yemekuygulamasi.ui.adapter.AnasayfaAdapter
import com.example.yemekuygulamasi.ui.viewmodel.AnasayfaViewmodel


class AnasayfaFragment : Fragment() {
    private lateinit var binding:FragmentAnasayfaBinding
    private lateinit var viewmodel:AnasayfaViewmodel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa, container, false)
        binding.anasayfaFragment = this
        binding.yemekAnaSayfaToolbar="Yemekler"
        viewmodel.yemeklerListesi.observe(viewLifecycleOwner){
            val adapter =AnasayfaAdapter(requireContext(),it)
            binding.yemekAdapter=adapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : AnasayfaViewmodel by viewModels()
        viewmodel=tempViewModel
    }

}