package com.smartwavettn.horoscope.ui

import android.content.ContentValues
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import com.smartwavettn.horoscope.base.local.Preferences
import com.smartwavettn.horoscope.ui.home.HomeFragment
import com.smartwavettn.scanqr.ui.splash.SplashFragment
import com.smartwavettn.horoscope.base.BaseActivity
import com.smartwavettn.horoscope.databinding.ActivityMainBinding
import org.joda.time.DateTime
import org.joda.time.chrono.BuddhistChronology
import java.util.Calendar

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var preferences: Preferences
    override fun getViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun init() {
        val westernDate = Calendar.getInstance().apply {
            set(2024, 9, 24)
        }
        val tibetanDate = TibetanCalendar.getTibetanDate(westernDate)
        println("Tibetan Date: ${tibetanDate.year}-${tibetanDate.month}-${tibetanDate.day}")
        println("Is Leap Month: ${tibetanDate.isLeapMonth}")
        println("Is Leap Day: ${tibetanDate.isLeapDay}")
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