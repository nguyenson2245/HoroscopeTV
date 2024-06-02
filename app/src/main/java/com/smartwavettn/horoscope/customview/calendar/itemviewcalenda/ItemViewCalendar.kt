package com.smartwavettn.horoscope.customview.calendar.itemviewcalenda

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartwavettn.horoscope.customview.model.DayModel
import com.smartwavettn.horoscope.databinding.ItemViewCalendarBinding

class ItemViewCalendar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private lateinit var binding: ItemViewCalendarBinding
    private lateinit var adapter: ItemViewCalendarAdapter

    init {
        init()
    }

    private fun init(){
        var listDay : ArrayList<DayModel> = arrayListOf()
        binding = ItemViewCalendarBinding.inflate(LayoutInflater.from(context), null, false)
        removeAllViews()
        addView(binding.root)
        adapter = ItemViewCalendarAdapter()
        binding.rcView.layoutManager = GridLayoutManager(context, 7, LinearLayoutManager.VERTICAL , false)
        binding.rcView.adapter = adapter
        addlistWeekView(listDay)
        adapter.submitList(listDay)

    }
    fun addlistWeekView(listDay: ArrayList<DayModel>){
        listDay.add(DayModel(weekOfDay = "S"))
        listDay.add(DayModel(weekOfDay = "M"))
        listDay.add(DayModel(weekOfDay = "T"))
        listDay.add(DayModel(weekOfDay = "W"))
        listDay.add(DayModel(weekOfDay = "T"))
        listDay.add(DayModel(weekOfDay = "F"))
        listDay.add(DayModel(weekOfDay = "S"))
        listDay.add(DayModel(weekOfDay = "S"))
        listDay.add(DayModel(weekOfDay = "S"))
        listDay.add(DayModel(weekOfDay = "S"))
    }
}