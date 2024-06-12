package com.smartwavettn.horoscope.customview.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.customview.calendar.itemviewcalendar.ItemViewCalendar
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
    private val scope = CoroutineScope(Job() + Dispatchers.Default)
    private val weekCalendarAdapter: WeekCalendarAdapter = WeekCalendarAdapter()
    var onClickSelected:((DayModel) -> Unit)? = null
    var dayModel: DayModel? = null
    private var isShowLunar: Boolean = false

    init {
        init()
    }

    private fun init(){
        val mothList: ArrayList<MothModel> = arrayListOf()

        binding = CalendarViewBinding.inflate(LayoutInflater.from(context), null , false)
        removeAllViews()
        addView(binding.root)

        binding.rvWeekday.adapter = weekCalendarAdapter
        binding.rvWeekday.layoutManager = GridLayoutManager(context, 7, LinearLayoutManager.VERTICAL, false)
        weekCalendarAdapter.submitList(getDataWeek())

        adapter = CalendarViewAdapter()
        binding.callMeasure.adapter = adapter
        binding.callMeasure.offscreenPageLimit = 3

        scope.launch(Dispatchers.Default){
            var currentDate = Calendar.getInstance()
            val endDate = Calendar.getInstance()

            currentDate.set(Calendar.YEAR, 2022)
            currentDate.set(Calendar.MONTH, 0)
            currentDate.set(Calendar.DAY_OF_MONTH, 1)

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
                Calendar.getInstance().let {
                    selectMoth(it.get(Calendar.MONTH) + 1, it.get(Calendar.YEAR))
                }
                viewTreeObserver.addOnGlobalLayoutListener {
                    onClickSelected?.let { getViewCurrentViewHolder()?.setOnClickItem (it) }
                }

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
                dayModel?.let { getViewCurrentViewHolder()?.onChangedCalendarSelect(it) }
                calendar.set(Calendar.YEAR,mothList.get(position).year.toInt())
                calendar.set(Calendar.MONTH,mothList.get(position).month.toInt()-1)

                binding.mothYear.text =  SimpleDateFormat("MMM").format(calendar.time)+"/"+ mothList.get(position).year
                binding.callMeasure.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                getViewCurrentViewHolder()?.setShowLunarAndCuttingHair()
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
    private fun selectMoth(month: Int, year: Int) {
        val listFitter = adapter.listItem.filter {
            month == it.month.toInt() && it.year.toInt() == year
        }
        if (listFitter.isNotEmpty())
            binding.callMeasure.setCurrentItem(adapter.listItem.indexOf(listFitter.first()), false)

    }
    fun setDaySelect(dayModel: DayModel) {
        val list = adapter.listItem.filter {
            it.year == dayModel.year && it.month == dayModel.month
        }
        if (list.isNotEmpty()) {
            binding.callMeasure.currentItem = adapter.listItem.lastIndexOf(list.first())

        }
        this.dayModel = dayModel
        getViewCurrentViewHolder()?.onChangedCalendarSelect?.invoke(dayModel)
    }

    fun getViewCurrentViewHolder(): ItemViewCalendar? {
        val recyclerView = binding.callMeasure[0] as RecyclerView
        val currentViewHolder =
            recyclerView.findViewHolderForAdapterPosition(binding.callMeasure.currentItem)
        val currentView = currentViewHolder?.itemView
        if (currentView != null) {
            return currentView.findViewById(R.id.item_calendar)
        }
        return null
    }
    fun setShowLunarAndCuttingHair(){
        getViewCurrentViewHolder()?.setShowLunarAndCuttingHair()
    }

}


