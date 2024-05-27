package com.smartwavettn.horoscope.ui.intro.introOne

import android.view.LayoutInflater
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.FragmentIntroOneBinding
import com.smartwavettn.horoscope.ui.intro.introTwo.IntroTwoFragment
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding
import org.joda.time.DateTime
import org.joda.time.chrono.BuddhistChronology


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