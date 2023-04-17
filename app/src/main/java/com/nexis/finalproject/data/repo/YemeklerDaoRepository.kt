package com.nexis.finalproject.data.repo

import androidx.lifecycle.MutableLiveData
import com.nexis.finalproject.data.entity.Yemekler
import com.nexis.finalproject.data.entity.YemeklerCevap
import com.nexis.finalproject.retrofit.ApiUtils
import com.nexis.finalproject.retrofit.ServicesDAO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerDaoRepository {

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
}