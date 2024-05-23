package com.smartwavettn.horoscope.ui.intro.introTwo

import android.view.LayoutInflater
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding
import com.smartwavettn.horoscope.databinding.FragmentIntroTwoBinding
import com.smartwavettn.horoscope.ui.utils.PickerLayoutManager

class IntroTwoFragment :BaseFragmentWithBinding<FragmentIntroTwoBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroTwoBinding {
      return FragmentIntroTwoBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {
        val pickerLayoutManager = PickerLayoutManager(requireContext(), PickerLayoutManager.HORIZONTAL, false)

    }

    override fun initAction() {

    }

}