package com.smartwavettn.horoscope.ui.home

import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.FragmentManager
import com.akexorcist.snaptimepicker.SnapTimePickerDialog
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.BaseViewModel
import java.util.Calendar

class HomeViewModel : BaseViewModel() {

    fun getListPersonaLiveData() = repository.getListLiveData()

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

}