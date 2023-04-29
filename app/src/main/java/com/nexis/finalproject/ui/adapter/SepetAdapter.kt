package com.nexis.finalproject.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexis.finalproject.R
import com.nexis.finalproject.data.entity.Sepet
import com.nexis.finalproject.databinding.CardTasarim2Binding
import com.nexis.finalproject.databinding.CardTasarimBinding
import com.nexis.finalproject.ui.viewmodel.SepetViewModel

class SepetAdapter(var mContext:Context, var sepetListesi:List<Sepet>, var viewModel:SepetViewModel) :
    RecyclerView.Adapter<SepetAdapter.SatirTasarimTutucu>(){


    inner class SatirTasarimTutucu(var binding: CardTasarim2Binding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatirTasarimTutucu {
        val binding:CardTasarim2Binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.card_tasarim2,parent,false)
        return SatirTasarimTutucu(binding)
    }

    override fun getItemCount(): Int {
        return sepetListesi.size
    }

    override fun onBindViewHolder(holder: SatirTasarimTutucu, position: Int) {
        val sepet = sepetListesi.get(position)
        println(sepet)
        val t = holder.binding


        t.adet = sepet.yemek_siparis_adet.toString()

        t.sepetNesnesi =sepet
        t.sepetFiyat = sepet.yemek_fiyat.toString()+ " TL"

        var URL = "http://kasimadalan.pe.hu/yemekler/resimler/${sepet.yemek_resim_adi}"
        Glide.with(mContext).load(URL).into(t.imageView2)


        t.chipAzalt.setOnClickListener {
            val text = t.textViewAdet.text.toString()
            t.adet = (text.toInt() - 1).toString()
        }

        t.chipArttir.setOnClickListener {
            val text = t.textViewAdet.text.toString()
            if(text.toInt() > 1){
                t.adet = (text.toInt() + 1).toString()
            }
        }
        t.imageViewSil.setOnClickListener {
            sepetSil(sepet.sepet_yemek_id)
        }
    }



    fun sepetSil(sepetYemekId:Int){
        viewModel.sepetSil(sepetYemekId)
    }
}