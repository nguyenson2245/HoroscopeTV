package com.smartwavettn.horoscope.broadcast

import android.Manifest
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.ui.MainActivity


class AlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {

        val intent: Intent = Intent(context, MainActivity::class.java)

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE)


        val builder = NotificationCompat.Builder(context, "channelId")
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(R.string.app_name.toString())
            .setContentText("Hom nay la mot ngay tot lanh")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManagerCompat = NotificationManagerCompat.from(context)

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        notificationManagerCompat.notify(getNotificationManager(), builder.build())


    }

    fun getNotificationManager() : Int {
        return System.currentTimeMillis().toInt()
    }


}