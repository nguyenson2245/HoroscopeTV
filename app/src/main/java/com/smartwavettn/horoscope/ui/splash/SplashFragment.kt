package com.smartwavettn.scanqr.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.smartwavettn.horoscope.base.local.Preferences
import com.smartwavettn.horoscope.databinding.FragmentSplashBinding
import com.smartwavettn.horoscope.ui.home.HomeFragment
import com.smartwavettn.horoscope.ui.intro.introTwo.IntroTwoFragment
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class SplashFragment : BaseFragmentWithBinding<FragmentSplashBinding>() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private lateinit var preferences: Preferences

    private val viewModel: SplashViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater)
    }

    override fun init() {
        preferences = Preferences.getInstance(requireActivity())

        Handler(Looper.getMainLooper()).postDelayed({
            val bundle = Bundle()
            bundle.putString("checkFragment", "slashFragment")
            openFragment(HomeFragment::class.java, bundle, false)
        }, 1000)
    }

    override fun initData() {

    }

    override fun initAction() {

    }


}


