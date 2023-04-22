package com.example.yemekuygulamasi.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.data.entitiy.Yemekler
import com.example.yemekuygulamasi.databinding.RvAnasayfaItemBinding
import com.example.yemekuygulamasi.ui.fragment.AnasayfaFragmentDirections
import com.squareup.picasso.Picasso

class AramaAdapter(var mContext: Context, var liste:List<Yemekler> ):RecyclerView.Adapter<AramaAdapter.ViewHolder>() {
    inner class ViewHolder(var binding : RvAnasayfaItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:RvAnasayfaItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.rv_anasayfa_item,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gelenYemek=liste.get(position)
        val t =holder.binding
        t.yemekNesnesi=gelenYemek
        t.textViewFiyat.text="${gelenYemek.yemek_fiyat.toString()} ₺"
        t.cardAna.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.toDetay(yemek = gelenYemek)
            Navigation.findNavController(it).navigate(gecis)

        }
        val url="http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"

        if(gelenYemek.yemek_resim_adi !=null){
            Picasso.get().load(url).into(t.imageViewYemek)
        }else{
            Log.e("Resim","HAta")
        }

    }

}