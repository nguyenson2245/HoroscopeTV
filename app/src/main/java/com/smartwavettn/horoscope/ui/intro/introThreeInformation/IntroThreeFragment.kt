package com.smartwavettn.horoscope.ui.intro.introThreeInformation

import android.view.LayoutInflater
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.FragmentIntroThreeBinding
import com.smartwavettn.horoscope.ui.intro.introFourTopic.IntroFourTopicFragment
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding


class IntroThreeFragment : BaseFragmentWithBinding<FragmentIntroThreeBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroThreeBinding {
        return FragmentIntroThreeBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {
        binding.btnContinue.click {
            openFragment(IntroFourTopicFragment::class.java,null,true)
        }
    }
}