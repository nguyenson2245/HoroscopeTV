package com.example.horoscope.customview

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.smartwavettn.horoscope.databinding.CalendertDayBinding

class CalanderDay(context: Context) : FrameLayout(context) {
    private lateinit var binding : CalendertDayBinding
    private lateinit var adapter: CalenderDayAdapter

    init {
        initView()
    }
    private fun initView() {
        binding = CalendertDayBinding.inflate(LayoutInflater.from(context))
        adapter = CalenderDayAdapter()
        removeAllViews()
        addView(binding.root)
    }


}