package com.smartwavettn.horoscope.customview.calendar.itemviewcalenda

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartwavettn.horoscope.customview.model.DayModel
import com.smartwavettn.horoscope.databinding.ItemViewCalendarBinding
import java.util.Calendar

class ItemViewCalendar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private lateinit var binding: ItemViewCalendarBinding
    private lateinit var adapter: ItemViewCalendarAdapter
    private var year: Int = Calendar.getInstance().get(Calendar.YEAR)
    private var month: Int = Calendar.getInstance().get(Calendar.MONTH)



    private fun init(){
        var listDay : ArrayList<DayModel> = arrayListOf()
        binding = ItemViewCalendarBinding.inflate(LayoutInflater.from(context))
        removeAllViews()
        addView(binding.root)

        adapter = ItemViewCalendarAdapter()
        binding.rcView.layoutManager = GridLayoutManager(context, 7, LinearLayoutManager.VERTICAL , false)
        binding.rcView.adapter = adapter
        addlistWeekView(month, year, listDay)
        adapter.submitList(listDay)
    }
     fun setMonthAndYear(month: Int?, year: Int?){
         if (month != null) {
             this.month = month
         }
         if (year != null) {
             this.year = year
         }
        init()
    }

    fun addlistWeekView(month: Int, year: Int, listDay: ArrayList<DayModel>) {

        getAllDaysInMonth(year, month, listDay)

    }

        fun getAllDaysInMonth(year: Int, month: Int,listDay: ArrayList<DayModel>) : ArrayList<DayModel>{
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        for (i in 1 until firstDayOfWeek) {
            listDay.add(DayModel())
        }


        for (day in 1..daysInMonth) {
          listDay.add(DayModel(day = day.toString(), month = month.toString(), year= year.toString()))
        }
        return listDay
    }



}