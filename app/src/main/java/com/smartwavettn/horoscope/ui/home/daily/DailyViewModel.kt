package com.smartwavettn.horoscope.ui.home.daily

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.model.Daily
import com.smartwavettn.horoscope.ui.utils.DataJson
import kotlin.math.log

class DailyViewModel : ViewModel() {

    private val listSettings: ArrayList<Daily> = arrayListOf()
    val listAddSettings: MutableLiveData<ArrayList<Daily>> = MutableLiveData()


    fun initData(context: Context, position: Int) {
        listSettings.clear()
        listSettings.add(Daily(title = "Personal indicators"))
        listSettings.add(
            Daily(
                title = "Health", content = DataJson.getValueByKey(
                    context = context,
                    "Message-Personal-Day-${(position % 3)}"
                ) ?: "", icon = R.drawable.health
            )
        )
        listSettings.add(
            Daily(
                title = "Business",
                content = DataJson.getValueByKey(
                    context = context,
                    "Message-Group-Constellations-${(position % 7)}"
                ) ?: "", icon = R.drawable.business
            )
        )
        listSettings.add(Daily(title = "General indicators"))
        listSettings.add(
            Daily(
                title = "Situation",
                content = DataJson.getValueByKey(
                    context = context,
                    "Message-Big-Combo-${(position % 28)+1}"
                ) ?: "", icon = R.drawable.situation
            )
        )
        listSettings.add(
            Daily(
                title = "Lunar day",
                content = DataJson.getValueByKey(
                    context = context,
                    "Moon-Day-${(position % 30)+1}"
                ) ?: "",
                icon = R.drawable.lunadayz
            )
        )

        listSettings.add(
            Daily(
                title = "Hair cutting",
                content = DataJson.getValueByKey(
                    context = context,
                    "Haircut-Day-${(position % 30)+1}"
                ) ?: "",
                icon = R.drawable.cuttinghairz
            )
        )

        listSettings.add(
            Daily(
                title = "Clothing color",
                content = DataJson.getValueByKey(
                    context = context,
                    "Sources-${(position % 18)+ 1}"
                ) ?: "",
                icon = R.drawable.clothing
            )
        )

        listSettings.add(
            Daily(
                title = "Location of La energy in the body",
                content = DataJson.getValueByKey(
                    context = context,
                    "La_position_in_the_body"
                ) ?: "",
                icon = R.drawable.bodyz
            )
        )
        listAddSettings.postValue(listSettings)
    }

}