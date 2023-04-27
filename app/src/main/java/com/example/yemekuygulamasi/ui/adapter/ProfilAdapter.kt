package com.example.yemekuygulamasi.ui.adapter

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.data.entitiy.Siparis
import com.example.yemekuygulamasi.data.entitiy.SiparisCek
import com.example.yemekuygulamasi.databinding.RvFavorilerItemBinding
import com.example.yemekuygulamasi.databinding.RvSiparisItemBinding
import com.example.yemekuygulamasi.ui.fragment.ProfilFragmentDirections
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ProfilAdapter(var mContext: Context, var list:List<Siparis>) : RecyclerView.Adapter<ProfilAdapter.ViewHolder>() {

    inner  class ViewHolder(var binding : RvSiparisItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RvSiparisItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.rv_siparis_item,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val gelenSiparis = list.get(position)
      val t = holder.binding
        val tarih =tarih(gelenSiparis.tarih)
        t.textViewDate.text=tarih


        t.cardViewSiparis.setOnClickListener {
            val gecis = ProfilFragmentDirections.toSiparisDetay(gelenSiparis.siparis_id)
            Navigation.findNavController(it).navigate(gecis)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun tarih(tarih:String):String{
        val formatter = DateTimeFormatter.ofPattern("HH-dd-MM-yyyy")
        val formatliTarih = tarih.format(formatter)
        return  formatliTarih
    }
}