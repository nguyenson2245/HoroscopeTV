package com.smartwavettn.horoscope.customview.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.smartwavettn.horoscope.customview.customcalenday.CalenderDayAdapter
import com.smartwavettn.horoscope.customview.model.MothModel
import com.smartwavettn.horoscope.databinding.CalendarViewBinding

class CaladarView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private lateinit var binding : CalendarViewBinding
    private lateinit var adapter: CalendarViewAdapter
    init {
        init()
    }
    private fun init(){
        binding = CalendarViewBinding.inflate(LayoutInflater.from(context), null , false)
        removeAllViews()
        addView(binding.root)

        adapter = CalendarViewAdapter()
        binding.callMeasure.adapter = adapter
        adapter.submitList(arrayListOf(MothModel(1,2024)))
    }
}