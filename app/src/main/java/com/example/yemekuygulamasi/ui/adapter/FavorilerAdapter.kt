package com.example.yemekuygulamasi.ui.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.data.entitiy.Favoriler
import com.example.yemekuygulamasi.databinding.RvAnasayfaItemBinding
import com.example.yemekuygulamasi.databinding.RvFavorilerItemBinding
import com.example.yemekuygulamasi.ui.fragment.AnasayfaFragmentDirections
import com.example.yemekuygulamasi.ui.viewmodel.FavorilerViewModel
import com.squareup.picasso.Picasso

class FavorilerAdapter(var mContext:Context,var list : List<Favoriler>,var activity:Activity,var viewModel: FavorilerViewModel) : RecyclerView.Adapter<FavorilerAdapter.ViewHolder>() {
    inner class ViewHolder(var binding : RvFavorilerItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: RvFavorilerItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.rv_favoriler_item,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favoriYemek=list.get(position)
        val t =holder.binding
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${favoriYemek.yemek_resim_adi}"
        t.textViewAd.text= favoriYemek.yemek_adi.toString()
        t.textViewFiyat.text="${favoriYemek.yemek_fiyat} tl"
        gorselGoster(url,t)
        t.imageViewSilFav.setOnClickListener {
            viewModel.favoriSil(favoriYemek.yemek_id)

        }

    }
    fun gorselGoster(url:String,binding:RvFavorilerItemBinding){
        Glide.with(activity).load(url).override(150,150).into(binding.imageViewYemek)

    }



}