package com.smartwavettn.horoscope.ui

import android.app.Application
import com.smartwavettn.horoscope.base.local.Preferences

class MainApp : Application() {
    private lateinit var preferences: Preferences
    override fun onCreate() {
        super.onCreate()
        preferences = Preferences.getInstance(this)
        if (!preferences.firstInstall){
            preferences.firstInstall  = true
        }
    }


    companion object{
        private var instance: MainApp? = null
        fun getInstance(): MainApp{
            if (instance == null)
                instance = MainApp()
            return instance!!
        }
    }
}