package com.smartwavettn.horoscope.ui.intro.introTwo

import androidx.lifecycle.MutableLiveData
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.model.Avatar
import com.smartwavettn.socialmedia.base.BaseViewModel

class IntroTwoViewModel : BaseViewModel() {

    private val avatar: ArrayList<Avatar> = arrayListOf()
    val avatarLiveData: MutableLiveData<ArrayList<Avatar>> = MutableLiveData()

    fun initDataAvatar() {
        avatar.add(Avatar(R.drawable.intro1, ShareInOtherAppsFragment::class.java))


        listCreateQRLiveData.postValue(listCreateQR)
    }
}