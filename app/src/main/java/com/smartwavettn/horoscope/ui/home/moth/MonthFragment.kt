package com.smartwavettn.horoscope.ui.home.moth

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.smartwavettn.horoscope.databinding.FragmentMothBinding
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class MothFragment : BaseFragmentWithBinding<FragmentMothBinding>() {

    companion object {
        fun newInstance() = MothFragment()
    }

    private val viewModel: MothViewModel by viewModels()


    override fun getViewBinding(inflater: LayoutInflater): FragmentMothBinding {
        return FragmentMothBinding.inflate(inflater)
    }


    override fun init() {
    }

    override fun initData() {

    }

    override fun initAction() {

    }
}