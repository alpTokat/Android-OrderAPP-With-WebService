package com.nexis.finalproject.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexis.finalproject.data.entity.Sepet
import com.nexis.finalproject.data.repo.YemeklerDaoRepository

class SepetViewModel:ViewModel() {

    val yrepo = YemeklerDaoRepository()
    val sepetListesi:MutableLiveData<List<Sepet>>



    init {
        sepetYukle()
        sepetListesi = yrepo.sepetYemekleriGetir()
    }

    fun sepetYukle(){
        yrepo.sepetGetir()
    }

    fun sepetSil(sepetYemekId:Int){
        yrepo.sepetYemekSil(sepetYemekId)
    }
}