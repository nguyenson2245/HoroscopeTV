package com.smartwavettn.horoscope.ui.home.year

import android.util.Log
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartwavettn.horoscope.databinding.FragmentYearBinding
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class YearFragment : BaseFragmentWithBinding<FragmentYearBinding>() {

    companion object {
        fun newInstance() = YearFragment()
    }

    private val viewModel: YearViewModel by viewModels()
    private lateinit var adapter: YearAdapter


    override fun getViewBinding(inflater: LayoutInflater): FragmentYearBinding {
       return  FragmentYearBinding.inflate(inflater)
    }

    override fun init() {
        context?.let { viewModel.init(it) }
    }

    override fun initData() {
        adapter = YearAdapter { year, position ->

        }
        binding.rvView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvView.adapter = adapter
        binding.rvView.setHasFixedSize(true)

        viewModel.initDataCreateQr()
        viewModel.listYearLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun initAction() {

    }
}