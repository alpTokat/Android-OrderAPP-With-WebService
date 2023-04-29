package com.nexis.finalproject.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nexis.finalproject.R
import com.nexis.finalproject.databinding.FragmentAnasayfaBinding
import com.nexis.finalproject.ui.adapter.YemeklerAdapter
import com.nexis.finalproject.ui.viewmodel.AnasayfaViewModel

class AnasayfaFragment : Fragment() {

    private lateinit var binding:FragmentAnasayfaBinding

    private lateinit var viewModel: AnasayfaViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa, container,false)

        binding.rv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            val adapter = YemeklerAdapter(requireContext(), it)
            Log.e("Yemekler Listesi ",adapter.yemeklerListesi.get(0).toString())
            binding.yemeklerAdapter = adapter
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:AnasayfaViewModel by viewModels()
        viewModel = tempViewModel
    }


}