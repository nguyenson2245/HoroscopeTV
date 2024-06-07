package com.smartwavettn.horoscope.ui.home.moth

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.smartwavettn.horoscope.databinding.FragmentMonthBinding
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class MothFragment : BaseFragmentWithBinding<FragmentMonthBinding>() {

    companion object {
        fun newInstance() = MothFragment()
    }

    private val viewModel: MothViewModel by viewModels()


    override fun getViewBinding(inflater: LayoutInflater): FragmentMonthBinding {
        return FragmentMonthBinding.inflate(inflater)
    }


    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {

    }
}