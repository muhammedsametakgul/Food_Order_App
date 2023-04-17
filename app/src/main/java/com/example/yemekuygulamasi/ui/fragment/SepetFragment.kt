package com.example.yemekuygulamasi.ui.fragment

import android.database.DatabaseUtils
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.databinding.FragmentSepetBinding
import com.example.yemekuygulamasi.ui.adapter.AnasayfaAdapter
import com.example.yemekuygulamasi.ui.adapter.SepetAdapter
import com.example.yemekuygulamasi.ui.viewmodel.SepetViewmodel


class SepetFragment : Fragment() {
    private lateinit var binding:FragmentSepetBinding
    private lateinit var viewmodel:SepetViewmodel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sepet,container,false)
        binding.sepetToolbar="Sepetim"
        binding.sepetFragment = this
        binding.sepetToolbar="Yemekler"


        viewmodel.sepetler.observe(viewLifecycleOwner){
            val adapter = SepetAdapter(requireContext(),it,viewmodel)
            binding.sepetAdapter=adapter
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


}