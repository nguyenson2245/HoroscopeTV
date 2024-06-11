package com.smartwavettn.horoscope.ui.home

import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.smartwavettn.horoscope.base.BaseViewModel
import com.smartwavettn.horoscope.model.PersonalInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

class HomeViewModel : BaseViewModel() {
    var personal: MutableLiveData<PersonalInformation?> = MutableLiveData()
    fun getPersonalLiveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
               val personalInformation=  repository.getListProfiles().find { it.isProfile }
                Log.d(TAG, "getPersonalLiveData: " +  personalInformation?.name)
                personal.postValue(personalInformation)
            } catch (e: Throwable) {
                //
            }
        }
    }

    fun openEmailApp() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("trunghy1999@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of the email")
        intent.putExtra(Intent.EXTRA_TEXT, "Body of the email")
        context.startActivity(intent)
    }

    fun timeApp(setText: (String) -> Unit) {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            context,
            { _, selectedHour, selectedMinute ->
                setText.invoke("$selectedHour:$selectedMinute")
            },
            hour, minute, true
        )

        timePickerDialog.show()
    }
    fun getListData(): ArrayList<PersonalInformation>{
        return  repository.getListProfiles().toMutableList()  as ArrayList<PersonalInformation>
    }

}