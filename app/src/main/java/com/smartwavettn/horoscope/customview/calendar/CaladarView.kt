package com.smartwavettn.horoscope.customview.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
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
    private val weekCalendarAdapter: ItemViewWeekCalendarAdapter = ItemViewWeekCalendarAdapter()


    init {
        init()
    }
    private fun init(){
        binding = CalendarViewBinding.inflate(LayoutInflater.from(context), null , false)
        removeAllViews()
        addView(binding.root)
        binding.rvWeekday.adapter = weekCalendarAdapter
        binding.rvWeekday.layoutManager =
            GridLayoutManager(context, 7, LinearLayoutManager.VERTICAL, false)
        weekCalendarAdapter.submitList(getDataWeek())
        val mothList: ArrayList<MothModel> = arrayListOf()
        adapter = CalendarViewAdapter()
        binding.callMeasure.adapter = adapter
        binding.callMeasure.offscreenPageLimit = 3
        scope.launch(Dispatchers.Default){

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
                currentDate.add(Calendar.MONTH, 1)
            }
            withContext(Dispatchers.Main) {
                adapter.submitList(mothList)
            }

        }
        binding.back.setOnClickListener {
            if (binding.callMeasure.currentItem > 0)
                binding.callMeasure.currentItem = binding.callMeasure.currentItem -1
        }
        binding.next.setOnClickListener {
            if (binding.callMeasure.currentItem < mothList.size - 1)
                binding.callMeasure.currentItem = binding.callMeasure.currentItem + 1
        }
        binding.callMeasure.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.YEAR,mothList.get(position).year.toInt())
                calendar.set(Calendar.MONTH,mothList.get(position).month.toInt())
                binding.mothYear.text =  SimpleDateFormat("MMM").format(calendar.time)+"/"+ mothList.get(position).year
                binding.callMeasure.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                super.onPageSelected(position)
            }
        })

    }

    private fun getDataWeek(): ArrayList<DayModel> {
        val listDay = ArrayList<DayModel>()
        listDay.add(DayModel(weekOfDay = "S"))
        listDay.add(DayModel(weekOfDay = "M"))
        listDay.add(DayModel(weekOfDay = "T"))
        listDay.add(DayModel(weekOfDay = "W"))
        listDay.add(DayModel(weekOfDay = "T"))
        listDay.add(DayModel(weekOfDay = "F"))
        listDay.add(DayModel(weekOfDay = "S"))
        return listDay
    }
}