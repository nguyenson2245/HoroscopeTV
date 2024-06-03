package com.smartwavettn.horoscope.customview.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.smartwavettn.horoscope.customview.model.DayModel
import com.smartwavettn.horoscope.customview.model.MothModel
import com.smartwavettn.horoscope.databinding.CalendarViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar

class CaladarView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private lateinit var binding : CalendarViewBinding
    private lateinit var adapter: CalendarViewAdapter
    val scope = CoroutineScope(Job() + Dispatchers.Default)
    init {
        init()
    }
    private fun init(){
        binding = CalendarViewBinding.inflate(LayoutInflater.from(context), null , false)
        removeAllViews()
        addView(binding.root)

        adapter = CalendarViewAdapter()
        binding.callMeasure.adapter = adapter
        scope.launch(Dispatchers.Default){
            val mothList: ArrayList<MothModel> = arrayListOf()
            var currentDate = Calendar.getInstance()
            currentDate.set(Calendar.YEAR, 2022)
            currentDate.set(Calendar.MONTH, 0)
            currentDate.set(Calendar.DAY_OF_MONTH, 1)

            val endDate = Calendar.getInstance()
            endDate.set(Calendar.YEAR, 2028)
            endDate.set(Calendar.MONTH, 11)
            endDate.set(Calendar.DAY_OF_MONTH, 31)
            while (currentDate.time.time <= endDate.time.time) {
                val dayModel = MothModel(
                    month = SimpleDateFormat("MM").format(currentDate.time),
                    year = SimpleDateFormat("yyyy").format(currentDate.time),
                )
                mothList.add(dayModel)
                currentDate.add(Calendar.DAY_OF_MONTH, 1)
            }
            withContext(Dispatchers.Main) {
                adapter.submitList(mothList)
            }
        }

    }
}