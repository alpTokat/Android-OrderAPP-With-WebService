package com.nexis.finalproject.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nexis.finalproject.R
import com.nexis.finalproject.data.entity.Sepet
import com.nexis.finalproject.data.entity.Yemekler
import com.nexis.finalproject.databinding.FragmentDetayBinding
import com.nexis.finalproject.ui.viewmodel.DetayViewModel

class DetayFragment : Fragment() {

    private lateinit var binding:FragmentDetayBinding

    private lateinit var viewModel:DetayViewModel

    var adet = 1


    lateinit var gelenYemek:Yemekler
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detay, container, false)

        binding.detayFragment = this
        binding.adet = "1"

        val bundle:DetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek

        binding.textViewYemekAdi.text = gelenYemek.yemek_adi
        binding.textViewYemekFiyat.text = gelenYemek.yemek_fiyat.toString()

        binding.resimAdi = gelenYemek.yemek_resim_adi
        binding.buttonSepetGit.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.toSepet)
        }

        var URL = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        Glide.with(this).load(URL).into(binding.imageViewResim)



        return binding.root

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetayViewModel by viewModels()
        viewModel = tempViewModel
    }
    fun sepeteEkle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:String, yemek_siparis_adet:String){
        println("Sepete Tıkla Çalıştı")
        viewModel.sepetYukle()

        var adet = 0
        var liste = viewModel.sepetYemekleriGetir()
        if (liste != null) {
            for(item in liste){
                if(item.yemek_adi == yemek_adi){
                    viewModel.sepetYemekSil(item.sepet_yemek_id)
                    adet = item.yemek_siparis_adet
                }
            }
        }
        viewModel.sepetEkle(yemek_adi, yemek_resim_adi, yemek_fiyat.toInt(), yemek_siparis_adet.toInt()+adet)

    }

    fun adetArttır(){
        val text = binding.textViewAdet.text.toString()
        binding.adet = (text.toInt() + 1).toString()
    }
    fun adetAzalt(){
        val text = binding.textViewAdet.text.toString()
        if(text.toInt() > 1){
            binding.adet = (text.toInt() - 1).toString()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.sepetYukle()
        binding.buttonSepetGit.setOnClickListener { //AnasayfaFragmentDirections
            val gecis = DetayFragmentDirections.toSepet()
            Navigation.findNavController(it).navigate(gecis)
        }
    }
}