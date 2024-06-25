package com.smartwavettn.horoscope.broadcast

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.local.Preferences
import com.smartwavettn.horoscope.ui.MainActivity
import com.smartwavettn.horoscope.ui.utils.Constants
import com.smartwavettn.horoscope.ui.utils.LunarCoreHelper
import java.util.Calendar


class AlarmBroadcastReceiver : BroadcastReceiver() {
    val CHANNEL_ID = "Noty"

    override fun onReceive(context: Context, intent: Intent?) {
        val preferences = Preferences.getInstance(context)
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(context, MainActivity::class.java)
        val calendar = Calendar.getInstance()
        val lunarDay = LunarCoreHelper.convertSolar2Lunar(
            calendar.get(Calendar.DAY_OF_MONTH),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.YEAR),
            Constants.TIME_ZONE
        )
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(context.getString(R.string.app_name))
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setDefaults(NotificationCompat.DEFAULT_SOUND)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        if (preferences.getBoolean(Constants.LUNAR) == true && Constants.listDayPushNotification.any { it == lunarDay[0] }) {
            val lunarCoreHelper = LunarCoreHelper.convertSolar2Lunar(
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.YEAR),
                Constants.TIME_ZONE
            )
            builder.setContentText("Today is : " + lunarCoreHelper)
            notificationManager.notify(getNotificationManager(), builder.build())
        }
        val rangeDay = LunarCoreHelper.rateDay(
            LunarCoreHelper.getChiDayLunar(
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.YEAR)
            ), calendar.get(Calendar.MONTH) + 1
        )
        if (preferences.getBoolean(Constants.DAY_NICE) == true && rangeDay == "Good") {
            builder.setContentText("Today is a good day")
            notificationManager.notify(getNotificationManager(), builder.build())
        }
        if (preferences.getBoolean(Constants.DAY_BAD) == true && rangeDay == "Bad") {
            builder.setContentText("Today is a sad day")
            notificationManager.notify(getNotificationManager(), builder.build())
        }
    }


    fun getNotificationManager() : Int {
        return System.currentTimeMillis().toInt()
    }
}