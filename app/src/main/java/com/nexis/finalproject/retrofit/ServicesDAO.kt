package com.nexis.finalproject.retrofit

import com.nexis.finalproject.data.entity.CRUDCevap
import com.nexis.finalproject.data.entity.SepetCevap
import com.nexis.finalproject.data.entity.YemeklerCevap
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ServicesDAO {
    //http://kasimadalan.pe.hu/kisiler/tum_kisiler.php
    //http://kasimadalan.pe.hu/ -> BASE URL -> bu hepsinde aynı ikisini birleştireceğim
    //kisiler/tum_kisiler.php  -> Çalışacağımız kisim



    @GET("yemekler/tumYemekleriGetir.php")
    fun tumYemekleriGetir(): Call<YemeklerCevap>

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun sepetGetir(@Field("kullanici_adi") kullanici_adi:String):Call<SepetCevap>



    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun sepetEkle(@Field("yemek_adi") yemek_adi:String,
                          @Field("yemek_resim_adi") yemek_resim_adi:String,
                          @Field("yemek_fiyat") yemek_fiyat:Int,
                          @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                          @Field("kullanici_adi") kullanici_adi:String):Call<CRUDCevap>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun sepetYemekSil(@Field("sepet_yemek_id") sepet_yemek_id:Int,
                              @Field("kullanici_adi") kullanici_adi:String
    )
}