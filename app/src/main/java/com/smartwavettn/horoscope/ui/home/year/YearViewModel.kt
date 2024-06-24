package com.smartwavettn.horoscope.ui.home.year

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.smartwavettn.horoscope.base.BaseViewModel
import com.smartwavettn.horoscope.customview.model.Year
import com.smartwavettn.horoscope.ui.utils.DataJson

class YearViewModel : BaseViewModel() {

    private var listYear: ArrayList<Year> = arrayListOf()
    val listYearLiveData: MutableLiveData<ArrayList<Year>> = MutableLiveData()
    val processLuck: ObservableInt = ObservableInt(0)

    private var currentTibYear: Int = 0


    fun initDataYear() {
        listYear.clear()
        val yearMap = DataJson.readJsonYear(context)
        for ((year, yearData) in yearMap) {
            val tibYear = yearData.get("tibYear").asInt
            listYear.add(
                Year(
                    tibYear,
                    luEl = yearData.get("luEl").asInt * 10,
                    luMe = yearData.get("luMe").asInt * 10,
                    lungtaEl = yearData.get("lungtaEl").asInt * 10,
                    meva = yearData.get("meva").asInt * 10,
                    vanMe = yearData.get("vanMe").asInt*10
                )
            )
        }
        listYear = listYear.reversed().toMutableList() as ArrayList<Year>

        listYearLiveData.postValue(listYear)
        currentTibYear = listYear.last().tibYear

    }


}