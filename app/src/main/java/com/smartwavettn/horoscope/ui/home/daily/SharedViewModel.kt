package com.smartwavettn.horoscope.ui.home.daily

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartwavettn.horoscope.base.BaseViewModel

class SharedViewModel : ViewModel() {
    private val _currentCoin = MutableLiveData<Int>()
    val currentCoin: LiveData<Int> = _currentCoin

    fun updateCurrentCoin(newValue: Int) {
        _currentCoin.value = newValue
    }
}