package com.smartwavettn.horoscope.ui.home.daily

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.model.Daily

class DailyViewModel : ViewModel() {

    private val listSettings: ArrayList<Daily> = arrayListOf()
    val listAddSettings: MutableLiveData<ArrayList<Daily>> = MutableLiveData()


    fun initData(context: Context) {
        listSettings.clear()

        listSettings.add(Daily(title = "Personal indicators"))
        listSettings.add(Daily(title = "Health", content = "Brings success in performing tasks, doing business, and competing",icon = R.drawable.health))
        listSettings.add(Daily(title = "Business", content = "Mang lại thành công trong thực thi nhiệm vụ, kinh doanh, thi đấu Mang lại thành công trong thực thi nhiệm vụ, kinh doanh",icon = R.drawable.business))
        listSettings.add(Daily(title = "General indicators"))
        listSettings.add(Daily(title = "Situation", content = "Mang lại thành công trong thực thi nhiệm vụ, kinh doanh, thi đấu",icon = R.drawable.situation))
        listSettings.add(Daily(title = "Lunar day", content = "Mang lại thành công trong thực thi nhiệm vụ, kinh doanh, thi đấu",icon = R.drawable.lunadayz))
        listSettings.add(Daily(title = "Hair cutting", content = "Mang lại thành công trong thực thi nhiệm vụ, kinh doanh, thi đấu",icon = R.drawable.cuttinghairz))
        listSettings.add(Daily(title = "Clothing color", content = "Mang lại thành công trong thực thi nhiệm vụ, kinh doanh, thi đấu",icon = R.drawable.clothing))
        listSettings.add(Daily(title = "Location of La energy in the body", content = "Mang lại thành công trong thực thi nhiệm vụ, kinh doanh, thi đấu",icon = R.drawable.bodyz))


        listAddSettings.postValue(listSettings)
    }

}