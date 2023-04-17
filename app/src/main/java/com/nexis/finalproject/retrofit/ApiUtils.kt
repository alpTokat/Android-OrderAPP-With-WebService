package com.nexis.finalproject.retrofit


class ApiUtils {
    companion object{
        val BASE_URL = "http://kasimadalan.pe.hu/"
        fun getServiceDao():ServicesDAO{
            return RetrofitClient.getClient(BASE_URL).create(ServicesDAO::class.java)
        }
    }

}
