package com.nexis.finalproject.data.entity

import java.io.Serializable

data class SepetCevap(var sepet_yemekler:List<Sepet>, var success:String):Serializable{
    override fun toString(): String {
        return super.toString()

    }
}