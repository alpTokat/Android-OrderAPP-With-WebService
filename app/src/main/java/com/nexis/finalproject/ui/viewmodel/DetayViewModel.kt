package com.nexis.finalproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.nexis.finalproject.data.repo.YemeklerDaoRepository

class DetayViewModel: ViewModel() {
    val ydao = YemeklerDaoRepository()


    fun sepetEkle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int){
        ydao.sepeteYemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet)
    }

}