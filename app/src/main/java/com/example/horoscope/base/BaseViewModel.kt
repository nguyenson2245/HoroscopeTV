package com.example.socialmedia.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel() : ViewModel() {
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
}