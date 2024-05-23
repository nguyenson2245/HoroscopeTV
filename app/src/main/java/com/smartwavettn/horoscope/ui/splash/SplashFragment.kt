package com.smartwavettn.scanqr.ui.splash

import androidx.fragment.app.viewModels
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.smartwavettn.horoscope.base.local.Preferences
import com.smartwavettn.horoscope.ui.intro.introOne.IntroOneFragment

import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding
import com.smartwavettn.horoscope.databinding.FragmentSplashBinding

class SplashFragment : BaseFragmentWithBinding<FragmentSplashBinding>() {

    companion object {
        fun newInstance() = SplashFragment()
    }
    private lateinit var preferences : Preferences

    private val viewModel: SplashViewModel by viewModels()


    override fun getViewBinding(inflater: LayoutInflater): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater)
    }

    override fun init() {
        preferences = Preferences.getInstance(requireActivity())
        Handler(Looper.getMainLooper()).postDelayed({
                openFragment(IntroOneFragment::class.java,null,true)
        }, 1000)
    }

    override fun initData() {

    }

    override fun initAction() {

    }
}