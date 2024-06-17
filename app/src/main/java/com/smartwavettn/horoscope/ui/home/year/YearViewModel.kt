package com.smartwavettn.horoscope.ui.home.year

import androidx.lifecycle.MutableLiveData
import com.smartwavettn.horoscope.base.BaseViewModel
import com.smartwavettn.horoscope.customview.model.Year

class YearViewModel : BaseViewModel() {


    private val listYear: ArrayList<Year> = arrayListOf()
    val listYearLiveData: MutableLiveData<ArrayList<Year>> = MutableLiveData()

    fun initDataCreateQr() {
        listYear.clear()
        for (year in 1924..2043) {
            listYear.add(Year(year))
        }
        listYearLiveData.postValue(listYear)
    }

}