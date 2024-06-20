package com.smartwavettn.horoscope.ui.home.year

import androidx.lifecycle.MutableLiveData
import com.smartwavettn.horoscope.base.BaseViewModel
import com.smartwavettn.horoscope.customview.model.Year
import com.smartwavettn.horoscope.ui.utils.DataJson

class YearViewModel : BaseViewModel() {

    private var listYear: ArrayList<Year> = arrayListOf()
    val listYearLiveData: MutableLiveData<ArrayList<Year>> = MutableLiveData()

    fun initDataCreateQr() {
        listYear.clear()
        val yearMap = DataJson.readJsonYear(context)
        for ((year, yearData) in yearMap) {
            val tibYear = yearData.get("tibYear").asInt
            listYear.add(Year(tibYear))
        }
        listYear = listYear.reversed().toMutableList() as ArrayList<Year>

        listYearLiveData.postValue(listYear)
    }

}