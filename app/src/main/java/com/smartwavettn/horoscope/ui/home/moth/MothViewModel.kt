package com.smartwavettn.horoscope.ui.home.moth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MothViewModel : ViewModel() {

    val listMothLiveData: MutableLiveData<ArrayList<Int>> = MutableLiveData()
    fun initData() {
        val listMoth: ArrayList<Int> = arrayListOf()
        for (i in 1 until 13) {
            listMoth.add(i)
        }
        listMothLiveData.postValue(listMoth)
    }

}