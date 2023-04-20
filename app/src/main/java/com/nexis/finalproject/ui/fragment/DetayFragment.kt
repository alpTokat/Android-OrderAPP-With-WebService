package com.nexis.finalproject.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nexis.finalproject.R
import com.nexis.finalproject.databinding.FragmentDetayBinding

class DetayFragment : Fragment() {

    private lateinit var binding:FragmentDetayBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detay, container, false)

        val bundle:DetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek

        binding.textViewYemekAdi.text = gelenYemek.yemek_adi
        binding.textViewYemekFiyat.text = gelenYemek.yemek_fiyat.toString()

        var URL = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        Glide.with(this).load(URL).into(binding.imageViewResim)


        return binding.root

    }
}