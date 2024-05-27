package com.smartwavettn.horoscope.base

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartwavettn.horoscope.repository.Repository

abstract class BaseViewModel() : ViewModel() {
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    protected lateinit var repository: Repository
    protected lateinit var context : Context

    fun init(context:Context){
        repository = Repository(context)
        this.context = context
    }
}