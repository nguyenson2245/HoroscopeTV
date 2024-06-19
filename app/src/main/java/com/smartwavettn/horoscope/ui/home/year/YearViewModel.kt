package com.smartwavettn.horoscope.ui.home.year

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.smartwavettn.horoscope.base.BaseViewModel
import com.smartwavettn.horoscope.customview.model.Year
import com.smartwavettn.horoscope.ui.utils.DataJson

class YearViewModel : BaseViewModel() {

    private val listYear: ArrayList<Year> = arrayListOf()
    val listYearLiveData: MutableLiveData<ArrayList<Year>> = MutableLiveData()

    fun initDataCreateQr() {
        listYear.clear()
        val yearMap = DataJson.readJsonYear(context)
        for ((year, yearData) in yearMap) {
            val tibYear = yearData.get("tibYear").asInt
            listYear.add(Year(tibYear))
        }

        listYearLiveData.postValue(listYear)
    }

}