package com.smartwavettn.horoscope.customview.calendar.itemviewcalenda

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.smartwavettn.horoscope.databinding.ItemViewCalendarBinding

class ItemViewCalendar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private lateinit var binding: ItemViewCalendarBinding
    init {
        init()
    }
    private fun init(){
        binding = ItemViewCalendarBinding.inflate(LayoutInflater.from(context))
    }
}