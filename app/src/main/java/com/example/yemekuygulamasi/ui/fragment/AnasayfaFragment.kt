package com.example.yemekuygulamasi.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.data.entitiy.YemekCevap
import com.example.yemekuygulamasi.data.entitiy.Yemekler
import com.example.yemekuygulamasi.databinding.FragmentAnasayfaBinding
import com.example.yemekuygulamasi.ui.adapter.AnasayfaAdapter
import com.example.yemekuygulamasi.ui.viewmodel.AnasayfaViewmodel


class AnasayfaFragment : Fragment(),SearchView.OnQueryTextListener {
    private lateinit var binding:FragmentAnasayfaBinding
    private lateinit var viewmodel:AnasayfaViewmodel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa, container, false)
        binding.anasayfaFragment=this
        binding.yemekTool=""

        //viewmodel.yemekleriYukle()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        requireActivity().addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.toolbar_menu,menu)
                val item = menu.findItem(R.id.action_ara)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@AnasayfaFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        },viewLifecycleOwner,Lifecycle.State.RESUMED)

        binding.anasayfaFragment = this
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


   /*override fun onResume() {
        super.onResume()
        viewmodel.yemekleriYukle()
    }*/
    override fun onQueryTextSubmit(query: String): Boolean {
        viewmodel.ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewmodel.ara(newText)
        return true
    }

    fun artanFiyat(){
        viewmodel.artanFiyat()
    }
    fun azalanFiyat(){
        viewmodel.azalanFiyat()
    }



}