package com.smartwavettn.horoscope.ui.home.daily

import android.view.LayoutInflater
import com.smartwavettn.horoscope.databinding.FragmentDailyBinding
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class DailyFragment : BaseFragmentWithBinding<FragmentDailyBinding>(){
    companion object {
        fun newInstance() = DailyFragment()
    }

    override fun getViewBinding(inflater: LayoutInflater): FragmentDailyBinding {
      return FragmentDailyBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {

    }


}