package com.nexis.finalproject.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.manager.LifecycleListener
import com.nexis.finalproject.R
import com.nexis.finalproject.databinding.FragmentSepetBinding
import com.nexis.finalproject.ui.adapter.SepetAdapter
import com.nexis.finalproject.ui.adapter.YemeklerAdapter
import com.nexis.finalproject.ui.viewmodel.DetayViewModel
import com.nexis.finalproject.ui.viewmodel.SepetViewModel

class SepetFragment : Fragment() {

    private lateinit var binding:FragmentSepetBinding


    private lateinit var viewModel: SepetViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var totalPrice = 0
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sepet, container, false)



        viewModel.sepetListesi.observe(viewLifecycleOwner){
            val adapter = SepetAdapter(requireContext(), it,viewModel)
            totalPrice = 0
            for(item in it){
                totalPrice += item.yemek_fiyat * item.yemek_siparis_adet
            }
            binding.sepetAdapter = adapter
            binding.toplamFiyat = "Tutar : $totalPrice TL"
        }

        binding.rvSepet.layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:SepetViewModel by viewModels()
        viewModel = tempViewModel
    }
}