package com.example.yemekuygulamasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.airbnb.lottie.LottieAnimationView
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.data.repo.AnimasyonRepository
import com.example.yemekuygulamasi.databinding.FragmentFavorilerBinding
import com.example.yemekuygulamasi.ui.adapter.FavorilerAdapter
import com.example.yemekuygulamasi.ui.viewmodel.FavorilerVMF
import com.example.yemekuygulamasi.ui.viewmodel.FavorilerViewModel
import com.google.android.material.snackbar.Snackbar


class FavorilerFragment : Fragment() {
    private lateinit var binding:FragmentFavorilerBinding
    private lateinit var viewModel: FavorilerViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_favoriler,container,false)
        //Observe data which comes from Local database with Room
        viewModel.favorilerListesi.observe(viewLifecycleOwner){
            val adapter = FavorilerAdapter(requireContext(),it,requireActivity(),viewModel)
            binding.favorilerAdapter=adapter
            val liste = it
            binding.imageViewTumSil.setOnClickListener {
                val snackbar = Snackbar.make(it, "Silmek istediğinize emin misiniz?", Snackbar.LENGTH_LONG)
                snackbar.setAction("Evet") {
                   hepsiniSil()
                }
                snackbar.show()
            }
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp : FavorilerViewModel by viewModels() {
            FavorilerVMF(requireActivity().application)
        }
        viewModel = temp
    }
    //Delete All
    fun hepsiniSil(){
        viewModel.favoriHepsiniSil()
    }

    //if one of items is deleted , it provides that our app interface
    override fun onResume() {
        super.onResume()
        viewModel.favoriYukle()
    }

}