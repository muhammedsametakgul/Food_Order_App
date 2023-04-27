package com.example.yemekuygulamasi.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.data.entitiy.Sepet
import com.example.yemekuygulamasi.databinding.RvFavorilerItemBinding
import com.example.yemekuygulamasi.databinding.RvSiparisDetayItemBinding

class SiparisDetayAdapter(var mContext : Context, var list: ArrayList<Sepet>,var activity :Activity) : RecyclerView.Adapter<SiparisDetayAdapter.ViewHolder>(){

    inner  class ViewHolder(var binding : RvSiparisDetayItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RvSiparisDetayItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.rv_siparis_detay_item,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val gelenSiparis = list.get(position)
        val t = holder.binding
        t.textViewAdet.text=gelenSiparis.yemek_siparis_adet.toString()
        t.textViewSepetYemekAd.text=gelenSiparis.yemek_adi
        t.textViewSepetFiyat.text=gelenSiparis.yemek_fiyat.toString()
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenSiparis.yemek_resim_adi}"

        gorselGoster(url,t)
    }


    fun gorselGoster(url:String,binding: RvSiparisDetayItemBinding){
        Glide.with(activity).load(url).override(150,150).into(binding.imageViewSepetResim)

    }
}