package com.smartwavettn.horoscope.customview.customcalenday

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.smartwavettn.horoscope.customview.customcalenday.model.DayModel
import com.smartwavettn.horoscope.databinding.CalendertDayBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar


class CalanderDay(context: Context, attr: AttributeSet) : FrameLayout(context, attr) {
    private lateinit var binding : CalendertDayBinding
    private lateinit var adapter: CalenderDayAdapter
    val scope = CoroutineScope(Job() + Dispatchers.Default)
    init {
        initView()
    }

    private fun initView() {
        scope.launch {
            val dayList: ArrayList<DayModel> = arrayListOf()
        binding = CalendertDayBinding.inflate(LayoutInflater.from(context))
        adapter = CalenderDayAdapter()
        removeAllViews()
        addView(binding.root)
        binding.callMeasure.adapter = adapter

        var currentDate = Calendar.getInstance()
        currentDate.set(Calendar.YEAR, 2022)
        currentDate.set(Calendar.MONTH, 0)
        currentDate.set(Calendar.DAY_OF_MONTH, 1)

        val endDate = Calendar.getInstance()
        endDate.set(Calendar.YEAR, 2028)
        endDate.set(Calendar.MONTH, 11)
        endDate.set(Calendar.DAY_OF_MONTH, 31)
        while (currentDate.time.time <= endDate.time.time) {
                val dayModel = DayModel(
                    day = SimpleDateFormat("dd").format(currentDate.time),
                    month = SimpleDateFormat("MM").format(currentDate.time),
                    year = SimpleDateFormat("yyyy").format(currentDate.time),
                )
                dayList.add(dayModel)
            currentDate.add(Calendar.DAY_OF_MONTH, 1)
            }
            withContext(Dispatchers.Main) {
                adapter.submitList(dayList)
            }
        }

    }
}


