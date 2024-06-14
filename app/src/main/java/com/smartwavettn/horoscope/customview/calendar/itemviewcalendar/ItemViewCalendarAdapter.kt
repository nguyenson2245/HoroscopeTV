package com.smartwavettn.horoscope.customview.calendar.itemviewcalendar

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.local.Preferences
import com.smartwavettn.horoscope.base.recyclerview.BaseRecyclerAdapter
import com.smartwavettn.horoscope.base.recyclerview.BaseViewHolder
import com.smartwavettn.horoscope.base.utils.gone
import com.smartwavettn.horoscope.base.utils.visible
import com.smartwavettn.horoscope.customview.model.DayModel
import com.smartwavettn.horoscope.databinding.ItemDayBinding
import com.smartwavettn.horoscope.ui.utils.Constants
import com.smartwavettn.horoscope.ui.utils.LunarCoreHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class ItemViewCalendarAdapter(context: Context, val onClickItem: (Int) -> Unit) :
    BaseRecyclerAdapter<DayModel, ItemViewCalendarAdapter.ViewHolder>() {
    val scope = CoroutineScope(Job() + Dispatchers.Default)
    private val preferences: Preferences = Preferences.getInstance(context)
    inner  class ViewHolder(val binding : ViewDataBinding) : BaseViewHolder<DayModel>(binding){
        @SuppressLint("UseCompatLoadingForDrawables")
        override fun bind(itemData: DayModel?) {
            super.bind(itemData)
            if (binding is ItemDayBinding){
                binding.title.text = itemData?.day
                    if (itemData?.day.isNullOrEmpty()){
                        binding.viewLayout.gone()
                        binding.underline.gone()
                    }else{
                        binding.root.background = if (itemData?.isSelected== true) itemView.context.getDrawable(R.drawable.bg_select_day) else null
                        binding.viewLayout.visible()
                        binding.underline.visible()

                        binding.statusMoon.visibility =
                            if (preferences.getBoolean(Constants.LUNAR) == true) View.VISIBLE else View.GONE

                        scope.launch(Dispatchers.Main) {
                            val calander = Calendar.getInstance().apply {
                                if (itemData != null) {
                                    set(Calendar.YEAR, itemData.year.toInt())
                                    set(Calendar.MONTH, itemData.month.toInt() - 1)
                                    set(Calendar.DAY_OF_MONTH, itemData.day.toInt())
                                }
                            }

                            val lunarDay = LunarCoreHelper.convertSolar2Lunar(
                                calander.get(Calendar.DAY_OF_MONTH),
                                calander.get(Calendar.MONTH) + 1,
                                calander.get(Calendar.YEAR),
                                Constants.TIME_ZONE
                            )


                            val rangeDay = LunarCoreHelper.rateDay(
                                LunarCoreHelper.getChiDayLunar(
                                    calander.get(Calendar.DAY_OF_MONTH),
                                    calander.get(Calendar.MONTH) + 1,
                                    calander.get(Calendar.YEAR)
                                ), calander.get(Calendar.MONTH) + 1
                            )
                            withContext(Dispatchers.Main) {
                                if (Constants.listTravel.any { it == lunarDay[0] }&& preferences.getBoolean(Constants.TRAVEL)== true) {
                                    binding.fly.visible()
                                }
                                else if (Constants.listDayHaircutting.any { it == lunarDay[0] } && preferences.getBoolean(Constants.CUTTING_HAIR)== true) {
                                    binding.cuttingHair.visible()
                                } else binding.cuttingHair.gone()

                                if (lunarDay[Constants.INDEX_0] == Constants.INDEX_15) {
                                    binding.statusMoon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_full_moon_pink))

                                } else {
                                    binding.statusMoon.setImageDrawable(null)
                                }
                                when (rangeDay) {
                                    "Good" -> {
                                        binding.underline.setImageDrawable(
                                            itemView.context.getDrawable(
                                                R.drawable.ic_underlined_green
                                            )
                                        )
                                    }

                                    "Bad" -> {
                                        binding.underline.setImageDrawable(
                                            itemView.context.getDrawable(
                                                R.drawable.ic_underline_red
                                            )
                                        )
                                    }

                                    else -> {
                                        binding.underline.setImageDrawable(null)
                                    }
                                }
                            }
                        }
                    }
                }
            onItemClickListener {
                if (itemData != null) {
                    onClickItem.invoke(adapterPosition)
                }
            }

        }

    }

    override fun getItemLayoutResource(viewType: Int): Int {
        return R.layout.item_day
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(getViewHolderDataBinding(parent, viewType))
    }
}