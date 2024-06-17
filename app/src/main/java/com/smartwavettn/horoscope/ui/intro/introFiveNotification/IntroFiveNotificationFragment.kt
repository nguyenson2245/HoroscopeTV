package com.smartwavettn.horoscope.ui.intro.introFiveNotification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.FragmentIntroFiveNotificationBinding
import com.smartwavettn.horoscope.ui.intro.introSix.IntroSixFragment
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class IntroFiveNotificationFragment :BaseFragmentWithBinding<FragmentIntroFiveNotificationBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroFiveNotificationBinding {
     return FragmentIntroFiveNotificationBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {
    }

    override fun initAction() {
        binding.noLatter.click {
            openFragment(IntroSixFragment::class.java,null,false)
        }

        binding.yesContinue.click {
            openFragment(IntroSixFragment::class.java,null,false)
        }

    }

}