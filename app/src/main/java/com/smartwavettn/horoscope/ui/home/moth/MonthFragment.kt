package com.smartwavettn.horoscope.ui.home.moth

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.smartwavettn.horoscope.databinding.FragmentMonthBinding
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class MothFragment : BaseFragmentWithBinding<FragmentMonthBinding>() {

    companion object {
        fun newInstance() = MothFragment()
    }

    private val viewModel: MothViewModel by viewModels()
    private lateinit var adapter: MothAdapter
    override fun getViewBinding(inflater: LayoutInflater): FragmentMonthBinding {
        return FragmentMonthBinding.inflate(inflater)
    }


    override fun init() {
        adapter = MothAdapter(){
            Log.d(TAG, "init:"+ adapter.positionSelected)
        }
        binding.rcView.adapter = adapter
        binding.rcView.layoutManager = GridLayoutManager(context, 5,GridLayoutManager.VERTICAL, false)
    }

    override fun initData() {
        viewModel.initData()
        viewModel.listMothLiveData.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    override fun initAction() {

    }
}