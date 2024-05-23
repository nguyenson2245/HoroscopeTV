package com.example.horoscope.ui

import android.os.Build
import android.view.LayoutInflater
import com.example.horoscope.base.local.Preferences
import com.example.horoscope.ui.home.HomeFragment
import com.example.scanqr.ui.splash.SplashFragment
import com.example.socialmedia.base.BaseActivity
import com.smartwavettn.horoscope.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var preferences: Preferences
    override fun getViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun init() {
        preferences = Preferences.getInstance(this)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S_V2)
            openFragment(SplashFragment::class.java, null, false)
        else {
            if (preferences.firstInstall) {
                openFragment(HomeFragment::class.java, null, false)
            }
        }
    }
}