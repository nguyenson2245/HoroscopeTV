package com.smartwavettn.scanqr.ui.splash

import android.content.ContentValues.TAG
import android.nfc.Tag
import androidx.fragment.app.viewModels
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import com.smartwavettn.horoscope.base.local.Preferences
import com.smartwavettn.horoscope.ui.intro.introOne.IntroOneFragment

import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding
import com.smartwavettn.horoscope.databinding.FragmentSplashBinding
import com.smartwavettn.horoscope.ui.home.HomeFragment
import com.smartwavettn.horoscope.ui.intro.introTwo.IntroTwoFragment
import org.joda.time.DateTime
import org.joda.time.chrono.BuddhistChronology

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
                openFragment(IntroTwoFragment::class.java,null,true)
        }, 1000)
    }

    override fun initData() {

    }

    override fun initAction() {

    }


}


