package com.smartwavettn.horoscope.customview

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.customview.model.DayModel
import com.smartwavettn.horoscope.databinding.CalendertDayBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class CalanderDay(context: Context, attr: AttributeSet) : FrameLayout(context, attr) {
    private lateinit var binding : CalendertDayBinding
    private lateinit var adapter: CalenderDayAdapter

    init {
        initView()
    }
    val dayList = arrayListOf<DayModel>()
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        binding = CalendertDayBinding.inflate(LayoutInflater.from(context))
        adapter = CalenderDayAdapter()
        removeAllViews()
        addView(binding.root)
        binding.callMeasure.adapter = adapter
        GlobalScope.launch(Dispatchers.Default){
            var currentDate = LocalDate.of(2018, 1, 1)
            val endDate = LocalDate.of(2028, 12, 31)

            while (currentDate <= endDate) {
                val dayModel = DayModel(
                    day = currentDate.format(DateTimeFormatter.ofPattern("dd")),
                    month = currentDate.format(DateTimeFormatter.ofPattern("MMMM")),
                    moon = R.drawable.ic_moon,
                    icon = currentDate.dayOfMonth  // Chỉnh sửa tùy theo nhu cầu
                )
                dayList.add(dayModel)
                // Chuyển sang ngày tiếp theo
                currentDate = currentDate.plusDays(1)
            }
            withContext(Dispatchers.Main){
                adapter.submitList(dayList)
            }
        }

    }


}