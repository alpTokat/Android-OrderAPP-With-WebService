package com.nexis.finalproject.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexis.finalproject.data.entity.Yemekler
import com.nexis.finalproject.data.repo.YemeklerDaoRepository

class AnasayfaViewModel:ViewModel() {
    val yrepo = YemeklerDaoRepository()
    val yemeklerListesi:MutableLiveData<List<Yemekler>>


    init {
        yemekleriYukle()
        yemeklerListesi =yrepo.yemekleriGetir()
    }


    fun yemekleriYukle(){
        yrepo.yemekleriYukle()
    }


}