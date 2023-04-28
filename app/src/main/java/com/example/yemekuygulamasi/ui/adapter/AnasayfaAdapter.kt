package com.example.yemekuygulamasi.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.data.entitiy.Favoriler
import com.example.yemekuygulamasi.data.entitiy.Yemekler
import com.example.yemekuygulamasi.databinding.RvAnasayfaItemBinding
import com.example.yemekuygulamasi.ui.fragment.AnasayfaFragmentDirections
import com.example.yemekuygulamasi.ui.viewmodel.AnasayfaViewmodel
import com.example.yemekuygulamasi.ui.viewmodel.FavorilerViewModel
import com.squareup.picasso.Picasso

class AnasayfaAdapter(var mContext: Context, var liste:List<Yemekler>,var favorilerViewmodel:FavorilerViewModel,var listeFav : List<Favoriler> ,var viewModel: AnasayfaViewmodel):RecyclerView.Adapter<AnasayfaAdapter.ViewHolder>() {
    inner class ViewHolder(var binding : RvAnasayfaItemBinding): RecyclerView.ViewHolder(binding.root){

    }

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
           Log.e("Resim","Hata")
        }

        t.imageViewAddFav.setOnClickListener {
        try {
            if(listeFav.size ==0){
                favorilerViewmodel.favoriEkle(gelenYemek.yemek_id,gelenYemek.yemek_adi,gelenYemek.yemek_resim_adi,gelenYemek.yemek_fiyat)
                Toast.makeText(mContext,"Ürün  favorilere eklendi",Toast.LENGTH_SHORT).show()

            }else{
                try {
                    for (i in 0..listeFav.size-1){
                        if(listeFav.get(i).yemek_adi  != gelenYemek.yemek_adi){
                            favorilerViewmodel.favoriEkle(gelenYemek.yemek_id,gelenYemek.yemek_adi,gelenYemek.yemek_resim_adi,gelenYemek.yemek_fiyat)
                            Toast.makeText(mContext,"Ürün  favorilere eklendi",Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(mContext,"Ürün zaten  favorilerde",Toast.LENGTH_SHORT).show()
                        }
                    }
                }catch (ex:java.lang.Exception){
                    Log.e("Hata",ex.toString())
                }
            }

        }catch (exp2:Exception){
            Log.e("İlk try",exp2.toString())
        }
        }


    }


}