package com.smartwavettn.horoscope.ui.intro.introThreeInformation

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

class IntroThreeViewModel : BaseViewModel() {
    fun getPersonalLiveData() = repository.getProFile()


}