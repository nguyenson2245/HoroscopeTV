package com.smartwavettn.horoscope.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.BaseActivity
import com.smartwavettn.horoscope.base.local.Preferences
import com.smartwavettn.horoscope.databinding.ActivityMainBinding
import com.smartwavettn.horoscope.ui.home.HomeFragment
import com.smartwavettn.horoscope.ui.home.HomeViewModel
import com.smartwavettn.horoscope.ui.home.daily.DailyFragment
import com.smartwavettn.horoscope.ui.home.daily.SharedViewModel
import com.smartwavettn.horoscope.ui.intro.introOne.IntroOneFragment
import com.smartwavettn.horoscope.ui.splash.SplashFragment
import com.smartwavettn.horoscope.ui.utils.KeyWord

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    private val sharedViewModel: SharedViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun init() {
        viewModel.init(this)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S_V2)
            openFragment(SplashFragment::class.java, null, false)
        else {
            if (viewModel.checkProfile())
                openFragment(HomeFragment::class.java, null, false)
            else {
                val bundle = Bundle()
                bundle.putString(KeyWord.checkFragment, KeyWord.slashFragment)
                openFragment(IntroOneFragment::class.java, bundle, false)
            }
        }
    }
}