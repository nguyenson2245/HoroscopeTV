package com.smartwavettn.horoscope.customview.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.smartwavettn.horoscope.databinding.CalendarViewBinding

class CaladarView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private lateinit var binding : CalendarViewBinding
    init {
        init()
    }
    private fun init(){
        binding = CalendarViewBinding.inflate(LayoutInflater.from(context))
        removeAllViews()
        addView(binding.root)
    }
}