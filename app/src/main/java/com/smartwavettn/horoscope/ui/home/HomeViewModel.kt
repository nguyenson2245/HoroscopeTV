package com.smartwavettn.horoscope.ui.home

import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.smartwavettn.horoscope.base.BaseViewModel
import com.smartwavettn.horoscope.base.local.Preferences
import com.smartwavettn.horoscope.model.PersonalInformation
import com.smartwavettn.horoscope.ui.utils.Constants
import com.smartwavettn.horoscope.ui.utils.KeyWord
import kotlinx.coroutines.launch
import java.util.Calendar

class HomeViewModel : BaseViewModel() {
    val currentTime = Calendar.getInstance()
    fun getPersonalLiveData() = repository.getProFile()
    fun getPersonalSelectedData() = repository.getProFileSelect()
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
        currentTime.set(Calendar.HOUR_OF_DAY,hour?: 0)
        currentTime.set(Calendar.MINUTE, preferences.getInt(Constants.MINUTE)?:0)
        setText.invoke(hour.toString() +":" + minutes.toString())
    }

    fun timeApp(setText: (String) -> Unit) {
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(
            context,
            { _, selectedHour, selectedMinute ->
                currentTime.set(Calendar.HOUR_OF_DAY, selectedHour)
                currentTime.set(Calendar.MINUTE, selectedMinute)
                val preferences : Preferences = Preferences.getInstance(context)
                preferences.setInt(Constants.MINUTE, selectedMinute)
                preferences.setInt(Constants.HOUR, selectedHour)
                setText.invoke("$selectedHour:$selectedMinute")
            },
            hour, minute, true
        )

        timePickerDialog.show()
    }

    fun updateProfile(personalInformation: PersonalInformation){
        viewModelScope.launch {
            try {
                repository.updateProfile(personalInformation)
            }catch (e: Throwable){

            }
        }

    }
}