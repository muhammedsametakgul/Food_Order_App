package com.example.yemekuygulamasi.ui.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.data.entitiy.Sepet
import com.example.yemekuygulamasi.databinding.RvAnasayfaItemBinding
import com.example.yemekuygulamasi.databinding.RvSepetItemBinding
import com.example.yemekuygulamasi.databinding.RvSiparisDetayItemBinding
import com.example.yemekuygulamasi.ui.viewmodel.SepetViewmodel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class SepetAdapter(var mContext:Context,var list:List<Sepet>,var viewModel: SepetViewmodel,var activity:Activity):RecyclerView.Adapter<SepetAdapter.ViewHolder>() {
    inner class ViewHolder(var binding:RvSepetItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RvSepetItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.rv_sepet_item,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sepetYemek=list.get(position)
        val t = holder.binding
        t.sepetNesnesi = sepetYemek
        val url="http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}"

        if(sepetYemek.yemek_resim_adi !=null){
        gorselGoster(url,t)
        }else{
            Log.e("Resim","HAta")
        }
        t.textViewAdet.text="${sepetYemek.yemek_siparis_adet.toString()} adet"
        var adet=sepetYemek.yemek_siparis_adet.toString().toInt()
        t.textViewSepetYemekAd.text=sepetYemek.yemek_adi
        t.textViewSepetFiyat.text="${sepetYemek.yemek_fiyat.toString()} ₺"


        t.imageViewSil.setOnClickListener {

              Snackbar.make(it,"${sepetYemek.yemek_adi} silinsin mi?",Snackbar.LENGTH_LONG)
                  .setAction("EVET"){
                      for (i in 1..adet){
                          sil(sepetYemek.sepet_yemek_id,"samet")}
                  }.show()
        }
    }
    fun sil(sepet_yemek_id:Int,kullanici_adi:String){
       viewModel.sil(sepet_yemek_id,kullanici_adi)
    }
    fun gorselGoster(url:String,binding: RvSepetItemBinding){
        Glide.with(activity).load(url).override(150,150).into(binding.imageViewSepetResim)

    }
}