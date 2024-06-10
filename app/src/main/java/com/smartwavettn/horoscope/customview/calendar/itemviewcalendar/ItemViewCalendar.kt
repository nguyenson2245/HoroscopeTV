package com.smartwavettn.horoscope.customview.calendar.itemviewcalendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartwavettn.horoscope.customview.model.DayModel
import com.smartwavettn.horoscope.databinding.ItemViewCalendarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.util.Calendar

class ItemViewCalendar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private lateinit var binding: ItemViewCalendarBinding
    private lateinit var adapter: ItemViewCalendarAdapter
    private var year: Int = Calendar.getInstance().get(Calendar.YEAR)
    private var month: Int = Calendar.getInstance().get(Calendar.MONTH)
    private var position: Int = 0
    val scope = CoroutineScope(Job() + Dispatchers.Default)


    var onChangedCalendarSelect: ((DayModel) -> Unit)? = null
    private var onClickSelected:((DayModel) -> Unit)? = null

    private fun init() {
        var listDay: ArrayList<DayModel> = arrayListOf()
        binding = ItemViewCalendarBinding.inflate(LayoutInflater.from(context))
        removeAllViews()
        addView(binding.root)

        adapter = ItemViewCalendarAdapter{
            listDay.forEach {
                it.isSelected = false
            }
            listDay.get(it).isSelected = true
            adapter.submitList(listDay, false)
            onClickSelected?.invoke(listDay.get(it))
            adapter.notifyItemChanged(it)
            adapter.notifyItemChanged(position)
            position = it
        }
        binding.rcView.layoutManager =
            GridLayoutManager(context, 7, LinearLayoutManager.VERTICAL, false)
        binding.rcView.adapter = adapter
        getAllDaysInMonth(year, month, listDay)
        setSelectedDay()
        adapter.submitList(listDay)
    }
     fun setOnClickItem(onClickItem: (DayModel)-> Unit){
        this.onClickSelected = onClickItem
    }

    fun setMonthAndYear(month: Int?, year: Int?) {
        if (month != null) {
            this.month = month
        }
        if (year != null) {
            this.year = year
        }
        init()
    }

    fun getAllDaysInMonth(
        year: Int,
        month: Int,
        listDay: ArrayList<DayModel>
    ): ArrayList<DayModel> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        for (i in 1 until firstDayOfWeek) {
            listDay.add(DayModel())
        }

        for (day in 1..daysInMonth) {
            listDay.add(
                DayModel(
                    day = day.toString(),
                    month = month.toString(),
                    year = year.toString(),
                    isSelected = day == Calendar.getInstance()
                        .get(Calendar.DAY_OF_MONTH) && month - 1 == Calendar.getInstance()
                        .get(Calendar.MONTH) && year == Calendar.getInstance().get(Calendar.YEAR)
                ).apply {
                    if (isSelected) {
                        position = listDay.size
                    }
                }

            )

        }
        return listDay
    }

    fun setSelectedDay() {
        onChangedCalendarSelect = { dayModel ->
            onChangedCalendarSelect(dayModel)
        }

    }

    fun onChangedCalendarSelect(dayModel: DayModel) {
            val lits = adapter.listItem.filter {
                it.day.toIntOrNull() == dayModel.day.toInt() && it.month.toIntOrNull() == dayModel.month.toInt() && it.year.toIntOrNull() == dayModel.year.toInt()
            }
            if (lits.size > 0) {
                val position = adapter.listItem.indexOf(lits.first())
                if (position != -1) {
                    adapter.listItem.forEach {
                        it.isSelected = false
                    }
                    adapter.listItem.get(position).isSelected = true
                    adapter.notifyItemChanged(this@ItemViewCalendar.position)
                    adapter.notifyItemChanged(position)
                    this@ItemViewCalendar.position = position
                }
            } else {
                if (position != -1) {
                    adapter.listItem[position].isSelected = false
                    adapter.notifyItemChanged(position)
                    position = -1
                }
            }

    }

}