package com.nexis.finalproject.data.repo

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.core.os.MessageCompat
import androidx.lifecycle.MutableLiveData
import com.nexis.finalproject.data.entity.CRUDCevap
import com.nexis.finalproject.data.entity.Yemekler
import com.nexis.finalproject.data.entity.YemeklerCevap
import com.nexis.finalproject.retrofit.ApiUtils
import com.nexis.finalproject.retrofit.ServicesDAO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerDaoRepository (){

    var yemeklerListesi:MutableLiveData<List<Yemekler>>
    var ydao:ServicesDAO



    init {
        yemeklerListesi = MutableLiveData()
        ydao =ApiUtils.getServiceDao()
    }


    fun yemekleriGetir():MutableLiveData<List<Yemekler>>{
        return yemeklerListesi
    }

    fun yemekleriYukle(){

        ydao.tumYemekleriGetir().enqueue(object: Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>, response: Response<YemeklerCevap>) {
                val liste = response.body()!!.yemekler
                yemeklerListesi.value = liste
            }

            override fun onFailure(call: Call<YemeklerCevap>, t: Throwable) {}
        })
    }

    fun sepeteYemekEkle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int){
        ydao.sepetEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet,"alp_tokat").enqueue(object : Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {

            }override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}
        })
    }
}