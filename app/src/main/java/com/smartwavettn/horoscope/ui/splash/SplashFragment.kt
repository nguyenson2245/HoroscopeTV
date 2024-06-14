package com.smartwavettn.horoscope.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.smartwavettn.horoscope.base.local.Preferences
import com.smartwavettn.horoscope.databinding.FragmentSplashBinding
import com.smartwavettn.horoscope.ui.home.HomeFragment
import com.smartwavettn.horoscope.ui.home.HomeViewModel
import com.smartwavettn.horoscope.ui.intro.introOne.IntroOneFragment
import com.smartwavettn.horoscope.ui.utils.KeyWord
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class SplashFragment : BaseFragmentWithBinding<FragmentSplashBinding>() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private lateinit var preferences: Preferences

    private val viewModel: HomeViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater)
    }

    override fun init() {
        preferences = Preferences.getInstance(requireActivity())
        viewModel.init(requireActivity())
        Handler(Looper.getMainLooper()).postDelayed({
         if (viewModel.getPersonalLiveData().value != null)
                openFragment(HomeFragment::class.java, null, false)
            else {
                val bundle = Bundle()
                bundle.putString(KeyWord.checkFragment, KeyWord.slashFragment)
                openFragment(IntroOneFragment::class.java, bundle, false)

            }

        }, 1000)
    }

    override fun initData() {

    }

    override fun initAction() {

    }


}


