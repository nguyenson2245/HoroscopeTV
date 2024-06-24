package com.smartwavettn.horoscope.ui.utils

import com.smartwavettn.horoscope.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Constants {
    const val HOUR = "HOUR"
    const val MINUTE = "MINUTE"
    const val DAY_BAD= "DAY_BAD"
    const val DAY_NICE= "DAY_NICE"
    const val LUNAR = "LUNAR"
    const val CUTTING_HAIR = "CUTTING_HAIR"
    const val TRAVEL = "TRAVEL"// STOPSHIP:
    const val NOTICES = "NOTICES"
    const val AM_PM = "AM_PM"
    const val INDEX_0 = 0
    const val INDEX_1 = 1
    const val INDEX_15 = 15
    const val INDEX_30 = 30
    const val TIME_ZONE = 7.0
    val listDayPushNotification= arrayListOf(10,15,25,30)
    val listDayHaircutting = arrayOf(3, 4, 5, 6, 8, 9, 10, 11, 13, 14, 15, 17, 19, 20, 22, 23, 24, 26, 27, 30)
    val listTravel = arrayOf(3, 4, 7, 9, 11, 13, 15, 16, 19, 20, 21, 28, 27, 28, 30)
    val signs = arrayOf(
        "Aries",
        "Taurus",
        "Gemini",
        "Cancer",
        "Leo",
        "Virgo",
        "Libra",
        "Scorpio",
        "Sagittarius",
        "Capricorn",
        "Aquarius",
        "Pisces"
    )

    val pictureN = arrayOf(
        R.drawable.c_mk,
        R.drawable.c_bb,
        R.drawable.c_sn,
        R.drawable.c_bd,
        R.drawable.c_kn,
        R.drawable.c_st,
        R.drawable.c_cg,
        R.drawable.c_sst,
        R.drawable.c_xn,
        R.drawable.c_tb,
        R.drawable.c_ty,
        R.drawable.c_nm,
    )
    private val dates = arrayOf(
        "03/21",
        "04/20",
        "05/21",
        "06/21",
        "07/23",
        "08/23",
        "09/23",
        "10/23",
        "11/22",
        "12/22",
        "01/20",
        "02/19"
    )
     fun getPositionZodiac(date: Date): Int {
        val sdf: SimpleDateFormat = SimpleDateFormat("MM/dd", Locale.getDefault())



        for (i in dates.indices) {
            try {
                val signDate: Date = sdf.parse(dates[i])
                if (date.compareTo(signDate) >= 0) {
                    return i
                }
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
        return 0
    }
}
