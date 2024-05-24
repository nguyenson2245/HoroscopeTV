package com.smartwavettn.horoscope.ui.intro.introSevenFriends

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.lifecycle.MutableLiveData
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.model.Avatar
import com.smartwavettn.socialmedia.base.BaseViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class IntroSevenViewModel : BaseViewModel() {

    private val avatar: ArrayList<Avatar> = arrayListOf()
    val listAvatarLiveData: MutableLiveData<ArrayList<Avatar>> = MutableLiveData()

    private val calendar = Calendar.getInstance()

    fun initDataAvatar() {
        avatar.add(Avatar(R.drawable.avatar1))
        avatar.add(Avatar(R.drawable.avatar2))
        avatar.add(Avatar(R.drawable.avatar3))
        avatar.add(Avatar(R.drawable.avatar4))
        avatar.add(Avatar(R.drawable.avatar5))
        avatar.add(Avatar(R.drawable.avatar6))
        avatar.add(Avatar(R.drawable.avatar7))
        avatar.add(Avatar(R.drawable.avatar8))
        avatar.add(Avatar(R.drawable.avatar9))
        avatar.add(Avatar(R.drawable.avatar10))
        avatar.add(Avatar(R.drawable.avatar11))
        avatar.add(Avatar(R.drawable.avatar12))
        avatar.add(Avatar(R.drawable.avatar13))
        avatar.add(Avatar(R.drawable.avatar14))
        avatar.add(Avatar(R.drawable.avatar15))
        avatar.add(Avatar(R.drawable.avatar16))
        avatar.add(Avatar(R.drawable.avatar17))
        avatar.add(Avatar(R.drawable.avatar18))
        avatar.add(Avatar(R.drawable.avatar19))
        avatar.add(Avatar(R.drawable.avatar20))
        avatar.add(Avatar(R.drawable.avatar21))
        avatar.add(Avatar(R.drawable.avatar22))
        avatar.add(Avatar(R.drawable.avatar23))
        avatar.add(Avatar(R.drawable.avatar24))
        avatar.add(Avatar(R.drawable.avatar25))
        avatar.add(Avatar(R.drawable.avatar26))
        avatar.add(Avatar(R.drawable.avatar27))


        listAvatarLiveData.postValue(avatar)
    }



}