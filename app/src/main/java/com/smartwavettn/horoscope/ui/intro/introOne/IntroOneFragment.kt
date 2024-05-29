package com.smartwavettn.horoscope.ui.intro.introOne

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.FragmentIntroOneBinding
import com.smartwavettn.horoscope.ui.intro.introTwo.IntroTwoFragment
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding


class IntroOneFragment : BaseFragmentWithBinding<FragmentIntroOneBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroOneBinding {
      return FragmentIntroOneBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {

            binding.getYourHoroscope.click {
                val bundle = Bundle()
                bundle.putBoolean("checkFragment" ,true)
                openFragment(IntroTwoFragment::class.java,bundle,true)

            }
    }

}