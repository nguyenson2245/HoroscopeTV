package com.smartwavettn.horoscope.ui.intro.introFourTopic

import android.view.LayoutInflater
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.FragmentIntroFourTopicBinding
import com.smartwavettn.horoscope.ui.intro.introFiveNotification.IntroFiveNotificationFragment
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

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
            openFragment(IntroFiveNotificationFragment::class.java,null,false)
        }
    }

}