package com.smartwavettn.horoscope.ui

import android.app.Application
import com.smartwavettn.horoscope.base.local.Preferences
import com.smartwavettn.horoscope.ui.utils.Constants

class MainApp : Application() {

    var preferences: Preferences? = null


    override fun onCreate() {
        super.onCreate()
        instance = this
        preferences = Preferences.getInstance(this)
        if (preferences?.firstInstall==false) {
            preferences?.firstInstall = true
            preferences?.setInt(Constants.MINUTE, 0)
            preferences?.setInt(Constants.HOUR, 10)
            preferences?.setValueCoin(10)
        }
    }

    companion object {
        private var instance: MainApp? = null
        fun getInstance(): MainApp {
            if (instance == null)
                instance = MainApp()
            return instance!!
        }
    }
}