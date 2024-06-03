package com.smartwavettn.horoscope.ui.intro.introTwo

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.model.PersonalInformation
import com.smartwavettn.horoscope.base.BaseViewModel
import com.smartwavettn.horoscope.local.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class IntroTwoViewModel : BaseViewModel() {

    var listAvatarResIds: ArrayList<Int> = arrayListOf()
    val listAvatarLiveData: MutableLiveData<ArrayList<Int>> = MutableLiveData()

    private val calendar = Calendar.getInstance()

    fun initDataAvatar() {

        listAvatarResIds = arrayListOf(
            R.drawable.avatar1,
            R.drawable.avatar2,
            R.drawable.avatar3,
            R.drawable.avatar4,
            R.drawable.avatar5,
            R.drawable.avatar6,
            R.drawable.avatar7,
            R.drawable.avatar8,
            R.drawable.avatar9,
            R.drawable.avatar10,
            R.drawable.avatar11,
            R.drawable.avatar12,
            R.drawable.avatar13,
            R.drawable.avatar14,
            R.drawable.avatar15,
            R.drawable.avatar16,
            R.drawable.avatar17,
            R.drawable.avatar18,
            R.drawable.avatar19,
            R.drawable.avatar20,
            R.drawable.avatar21,
            R.drawable.avatar22,
            R.drawable.avatar23,
            R.drawable.avatar24,
            R.drawable.avatar25,
            R.drawable.avatar26,
            R.drawable.avatar27
        )

        listAvatarLiveData.postValue(listAvatarResIds)
    }

    fun showDatePickerEnd(context: Context, setText: (String) -> Unit) {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                endDay(setText)
            }

        DatePickerDialog(
            context,
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun endDay(setText: (String) -> Unit) {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        setText.invoke(sdf.format(calendar.time))
    }

    fun addPersonalInformation(information: PersonalInformation) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.addPersonalInformation(information)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    fun isUserExist(note: PersonalInformation): Boolean {
        val list: List<PersonalInformation> =
            AppDatabase.getInstance(context).getInformationDao().checkName(note.name)
        return list != null && !list.isEmpty()
    }

    fun updateFriends(information: PersonalInformation) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.updatePersonalInformation(information)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }


}