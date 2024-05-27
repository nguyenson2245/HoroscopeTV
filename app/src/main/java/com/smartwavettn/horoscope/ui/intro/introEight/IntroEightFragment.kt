package com.smartwavettn.horoscope.ui.intro.introEight

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.databinding.FragmentIntroEightBinding
import com.smartwavettn.horoscope.ui.intro.introNine.IntroNineCalculatedFragment
import com.smartwavettn.horoscope.ui.intro.introOne.IntroOneFragment
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

        Handler(Looper.getMainLooper()).postDelayed({
            openFragment(IntroNineCalculatedFragment::class.java,null,true)
        }, 2500)

    }

}