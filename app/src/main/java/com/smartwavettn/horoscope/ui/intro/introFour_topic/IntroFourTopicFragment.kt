package com.smartwavettn.horoscope.ui.intro.introFour_topic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.FragmentIntroFourTopicBinding
import com.smartwavettn.horoscope.ui.intro.introFiveNotification.IntroFiveNotificationFragment
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding
import com.smartwavettn.socialmedia.base.BaseFragment

class IntroFourTopicFragment : BaseFragmentWithBinding<FragmentIntroFourTopicBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroFourTopicBinding {
      return FragmentIntroFourTopicBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {
        binding.btnContinue.click {
            openFragment(IntroFiveNotificationFragment::class.java,null,true)
        }
    }

}