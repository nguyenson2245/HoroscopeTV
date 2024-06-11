package com.smartwavettn.horoscope.ui

import android.os.Build
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import com.smartwavettn.horoscope.base.BaseActivity
import com.smartwavettn.horoscope.base.local.Preferences
import com.smartwavettn.horoscope.broadcast.AlarmBroadcastReceiver
import com.smartwavettn.horoscope.databinding.ActivityMainBinding
import com.smartwavettn.horoscope.ui.home.HomeFragment
import com.smartwavettn.scanqr.ui.splash.SplashFragment


class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var broadcastReceiver: AlarmBroadcastReceiver

    private lateinit var preferences: Preferences
    override fun getViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun init() {
        preferences = Preferences.getInstance(this)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S_V2)
            openFragment(SplashFragment::class.java, null, false)
        else {
            openFragment(HomeFragment::class.java, null, false)

        }

        createNotificationChanel()

    }

//    override fun onStop() {
//        super.onStop()
//        unregisterReceiver(broadcastReceiver);
//    }

    private fun createNotificationChanel() {

    }
}