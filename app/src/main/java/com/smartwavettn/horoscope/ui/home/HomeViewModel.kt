package com.smartwavettn.horoscope.ui.home

import com.smartwavettn.horoscope.base.BaseViewModel

class HomeViewModel : BaseViewModel() {

    fun getListPersonaLiveData() = repository.getListLiveData()

}