package com.smartwavettn.horoscope.ui.intro.introNine

import android.view.LayoutInflater
import com.smartwavettn.horoscope.databinding.FragmentIntroNineCalculatedBinding
import com.smartwavettn.horoscope.ui.intro.introTenEnd.IntroTenEndFragment
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class IntroNineCalculatedFragment :  BaseFragmentWithBinding<FragmentIntroNineCalculatedBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroNineCalculatedBinding {
        return FragmentIntroNineCalculatedBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {
        openFragment(IntroTenEndFragment::class.java,null,true)
    }

}