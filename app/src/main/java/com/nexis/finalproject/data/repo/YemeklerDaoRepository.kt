package com.nexis.finalproject.data.repo

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.core.os.MessageCompat
import androidx.lifecycle.MutableLiveData
import com.nexis.finalproject.data.entity.CRUDCevap
import com.nexis.finalproject.data.entity.Sepet
import com.nexis.finalproject.data.entity.SepetCevap
import com.nexis.finalproject.data.entity.Yemekler
import com.nexis.finalproject.data.entity.YemeklerCevap
import com.nexis.finalproject.retrofit.ApiUtils
import com.nexis.finalproject.retrofit.ServicesDAO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerDaoRepository (){

    var yemeklerListesi:MutableLiveData<List<Yemekler>>
    var sepetListesi:MutableLiveData<List<Sepet>>
    var ydao:ServicesDAO



    init {
        ydao =ApiUtils.getServiceDao()
        yemeklerListesi = MutableLiveData()
        sepetListesi = MutableLiveData()

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
                Log.e("Sepete Ekleme tamamlandı",response.body()!!.message)
            }override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}
        })
    }


    fun sepetYemekleriGetir():MutableLiveData<List<Sepet>>{
        return sepetListesi
    }

    fun sepetYemeklerDegerDondur(): List<Sepet>? {
        return sepetListesi.value
    }

    fun sepetGetir(){
        ydao.sepetGetir("alp_tokat").enqueue(object :Callback<SepetCevap>{
            override fun onResponse(call: Call<SepetCevap>, response: Response<SepetCevap>) {
                Log.e("Sepete Ekleme tamamlandı",response.body()!!.success)
                val liste = response.body()!!.sepet_yemekler
                println(liste)
                sepetListesi.value = liste
                //Log.e("Degerler", liste.get(0).toString())
            }

            override fun onFailure(call: Call<SepetCevap>, t: Throwable) {}

        })
    }

    fun sepetYemekSil(sepet_yemek_id:Int){
        ydao.sepetYemekSil(sepet_yemek_id,"alp_tokat").enqueue(object :Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
                Log.e("Sepete Ekleme tamamlandı",response.body()!!.success)
                sepetGetir()
            }
            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}
        })
    }
}