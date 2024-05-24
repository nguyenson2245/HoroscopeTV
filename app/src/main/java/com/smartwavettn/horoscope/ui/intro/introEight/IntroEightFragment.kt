package com.smartwavettn.horoscope.ui.intro.introEight

import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.databinding.FragmentIntroEightBinding
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class IntroEightFragment : BaseFragmentWithBinding<FragmentIntroEightBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroEightBinding {
       return FragmentIntroEightBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {
        val rotateAnimation = AnimationUtils.loadAnimation(requireActivity(), R.anim.rotate_animation)
        binding.imgRota.startAnimation(rotateAnimation)
    }

}