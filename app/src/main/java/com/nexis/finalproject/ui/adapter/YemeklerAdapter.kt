package com.nexis.finalproject.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexis.finalproject.R
import com.nexis.finalproject.data.entity.Yemekler
import com.nexis.finalproject.databinding.CardTasarimBinding

class YemeklerAdapter(var mContext:Context, var yemeklerListesi:List<Yemekler>) : RecyclerView.Adapter<YemeklerAdapter.SatirTasarimTutucu>(){


    inner class SatirTasarimTutucu(var binding:CardTasarimBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatirTasarimTutucu {
        val binding:CardTasarimBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.card_tasarim,parent,false)
        return SatirTasarimTutucu(binding)
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }

    override fun onBindViewHolder(holder: SatirTasarimTutucu, position: Int) {
        val yemek = yemeklerListesi.get(position)
        val t = holder.binding

        t.yemekNesnesi = yemek


    }
}