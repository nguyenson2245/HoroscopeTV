package com.smartwavettn.horoscope.ui.home

import android.content.Intent
import android.net.Uri
import com.smartwavettn.horoscope.base.BaseViewModel

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

}