package com.nexis.finalproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.nexis.finalproject.data.entity.Sepet
import com.nexis.finalproject.data.repo.YemeklerDaoRepository

class DetayViewModel: ViewModel() {
    val yrepo = YemeklerDaoRepository()


    fun sepetEkle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int){
        yrepo.sepeteYemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet)
    }

    fun sepetYemekleriGetir():List<Sepet>?{
        return yrepo.sepetYemeklerDegerDondur()
    }

    fun sepetYukle(){
        yrepo.sepetGetir()
    }

    fun sepetYemekSil(yemekId:Int){
        yrepo.sepetYemekSil(yemekId)
    }
}