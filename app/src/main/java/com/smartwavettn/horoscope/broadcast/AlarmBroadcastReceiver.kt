package com.smartwavettn.horoscope.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.smartwavettn.horoscope.R


class AlarmBroadcastReceiver : BroadcastReceiver() {

    //    override fun onReceive(context: Context, intent: Intent) {
//        if (intent.action == "MyAction") {
//            val notificationManager =
//                context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
//            if (notificationManager != null) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    val channel = NotificationChannel("201", "Notification", NotificationManager.IMPORTANCE_HIGH)
//                    channel.description = "Lovely"
//                    notificationManager.createNotificationChannel(channel)
//                }
//
//                val randomMessage = "getRandomMessage(context)"
//
//                val contentView = RemoteViews(context.packageName, R.layout.notification_layout)
//                contentView.setTextViewText(R.id.notification_title, "Daily")
//                contentView.setTextViewText(R.id.notification_message, randomMessage)
//
//
//                val builder = NotificationCompat.Builder(context, "201")
//                    .setSmallIcon(R.drawable.notification)
//                    .setContent(contentView)
//                    .setColor(Color.parseColor("#FF0000"))
//                    .setCategory(NotificationCompat.CATEGORY_ALARM)
//
//                notificationManager.notify(getNotificationId(), builder.build())
//            }
//        }
//    }
//
//    private fun getNotificationId(): Int {
//        return System.currentTimeMillis().toInt()
//    }
    override fun onReceive(context: Context, intent: Intent?) {
        val builder = NotificationCompat.Builder(context, "channelId")
            .setSmallIcon(R.drawable.aaaa)
            .setContentTitle(R.string.alarm.toString())
            .setContentText("conten ................................................")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManagerCompat  = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(123,builder.build())

    }


}