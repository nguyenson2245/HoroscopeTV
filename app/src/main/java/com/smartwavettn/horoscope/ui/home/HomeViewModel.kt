package com.smartwavettn.horoscope.ui.home

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.BaseViewModel
import com.smartwavettn.horoscope.base.local.Preferences
import com.smartwavettn.horoscope.model.PersonalInformation
import com.smartwavettn.horoscope.ui.MainActivity
import com.smartwavettn.horoscope.ui.utils.Constants
import com.smartwavettn.horoscope.ui.utils.KeyWord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

class HomeViewModel : BaseViewModel() {
    val currentTime = Calendar.getInstance()
    fun getPersonalLiveData() = repository.getProFile()
    fun checkProfile() = repository.checkProFile()

    fun openEmailApp() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse(KeyWord.mailTo)
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(KeyWord.mail))
        intent.putExtra(Intent.EXTRA_SUBJECT, KeyWord.subject)
        intent.putExtra(Intent.EXTRA_TEXT, KeyWord.body)
        context.startActivity(intent)
    }

    fun getListData(): ArrayList<PersonalInformation> {
        return repository.getListProfiles().toMutableList() as ArrayList<PersonalInformation>
    }
    fun getTime(setText: (String)-> Unit){
        val preferences : Preferences = Preferences.getInstance(context)
        val minutes = preferences.getInt(Constants.MINUTE)
        val hour =  preferences.getInt(Constants.HOUR)
        currentTime.set(Calendar.HOUR,hour?: 0)
        currentTime.set(Calendar.MINUTE, preferences.getInt(Constants.MINUTE)?:0)
        setText.invoke(hour.toString() +":" + minutes.toString())
    }

    fun timeApp(setText: (String) -> Unit) {
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(
            context,
            { _, selectedHour, selectedMinute ->
                currentTime.set(Calendar.HOUR, selectedHour)
                currentTime.set(Calendar.MINUTE, selectedMinute)
                setText.invoke("$selectedHour:$selectedMinute")
            },
            hour, minute, true
        )

        timePickerDialog.show()
    }

    private val CHANNEL_ID = "my_channel_id"
    private val NOTIFICATION_ID = 1
    fun createNotification(context: Context, intent: Intent) {
        // Create the NotificationChannel (r) {
        // Create the NotificationChannel (required for Android 8.0 and higher)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Channel"
            val descriptionText = "Today is a good day\n"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // Create the NotificationCompat.Builder
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle("Horoscope")
            .setContentText("Today is a good day\n")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Set the intent to open an activity when the notifiation is clicked

        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        builder.setContentIntent(pendingIntent)

        // Show the notification
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(getNotificationID(), builder.build())
    }

    // set nhiêu notification
    private fun getNotificationID(): Int {
        return (Date().time / 1000).toInt()
    }

}