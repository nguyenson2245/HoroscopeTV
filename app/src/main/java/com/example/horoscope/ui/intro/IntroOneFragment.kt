package com.example.horoscope.ui.intro

import android.view.LayoutInflater
import com.example.horoscope.databinding.FragmentIntroOneBinding
import com.example.scannerqr.base.BaseFragmentWithBinding
import com.example.socialmedia.base.utils.click

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
                openFragment(IntroTwoFragment::class.java,null,true)
            }
    }

}