package com.smartwavettn.horoscope.ui

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import com.smartwavettn.horoscope.base.BaseActivity
import com.smartwavettn.horoscope.base.local.Preferences
import com.smartwavettn.horoscope.databinding.ActivityMainBinding
import com.smartwavettn.horoscope.ui.home.HomeFragment
import com.smartwavettn.horoscope.ui.utils.LunarCoreHelper
import com.smartwavettn.scanqr.ui.splash.SplashFragment


class MainActivity : BaseActivity<ActivityMainBinding>() {
    val cycleStartYear = 1027 // Năm đầu tiên của chu kỳ 60 năm theo lịch Tây Tạng

    // Danh sách con giáp và nguyên tố

    private lateinit var preferences: Preferences
    override fun getViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun init() {
        lunarCoreTest(28, 5, 2024, 1, 7.00);
        preferences = Preferences.getInstance(this)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S_V2)
            openFragment(SplashFragment::class.java, null, false)
        else {

            openFragment(HomeFragment::class.java, null, false)

        }
    }

    private fun lunarCoreTest(
        day: Int,
        month: Int,
        year: Int,
        lunarLeapMonth: Int,
        timeZone: Double
    ) {
        val lunarDay = LunarCoreHelper.convertSolar2Lunar(day, month, year, timeZone)
        Log.d(
            TAG, """Lunar date (dd/mm/yyyy): ${lunarDay[0]}/${lunarDay[1]}/${lunarDay[2]}
Leap Month: ${if (lunarDay[3] == 1) "Yes" else "No"}
Can Chi: ${LunarCoreHelper.getCanDayLunar(day, month, year)} ${
                LunarCoreHelper.getChiDayLunar(
                    day,
                    month,
                    year
                )
            }
Chinese 干支: ${
                LunarCoreHelper.getChineseCanDayLunar(
                    day,
                    month,
                    year
                )
            }${LunarCoreHelper.getChineseChiDayLunar(day, month, year)}
Good/Bad Day: """ + LunarCoreHelper.rateDay(
                LunarCoreHelper.getChiDayLunar(day, month, year),
                lunarDay[1]
            )
        )


        val solarDay =
            LunarCoreHelper.convertLunar2Solar(day, month, year, lunarLeapMonth, timeZone)
        Log.d(
            TAG, """Solar date (dd/mm/yyyy): ${solarDay[0]}/${solarDay[1]}/${solarDay[2]}
Can Chi: ${
                LunarCoreHelper.getCanDayLunar(
                    solarDay[0],
                    solarDay[1],
                    solarDay[2]
                )
            } """ + LunarCoreHelper.getChiDayLunar(
                solarDay[0], solarDay[1], solarDay[2]
            ) + "\n" +
                    "Chinese 干支: " + LunarCoreHelper.getChineseCanDayLunar(
                solarDay[0],
                solarDay[1],
                solarDay[2]
            ) + LunarCoreHelper.getChineseChiDayLunar(
                solarDay[0], solarDay[1], solarDay[2]
            ) + "\n" +
                    "Good/Bad Day: " + LunarCoreHelper.rateDay(
                LunarCoreHelper.getChiDayLunar(
                    solarDay[0],
                    solarDay[1], solarDay[2]
                ), month
            )
        )
    }
}