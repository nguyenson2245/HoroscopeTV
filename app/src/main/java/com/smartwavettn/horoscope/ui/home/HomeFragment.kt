package com.smartwavettn.horoscope.ui.home

import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding
import com.smartwavettn.horoscope.databinding.FragmentHomeBinding

class HomeFragment : BaseFragmentWithBinding<FragmentHomeBinding>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {
    }

    override fun initAction() {
    }
}