package com.smartwavettn.horoscope.customview.customcalenday

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.ViewDataBinding
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.recyclerview.BaseRecyclerAdapter
import com.smartwavettn.horoscope.base.recyclerview.BaseViewHolder
import com.smartwavettn.horoscope.customview.model.DayModel
import com.smartwavettn.horoscope.databinding.ItemDayMoonBinding
import com.smartwavettn.horoscope.ui.utils.Constants
import com.smartwavettn.horoscope.ui.utils.LunarCoreHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar

class CalenderDayAdapter : BaseRecyclerAdapter<DayModel, CalenderDayAdapter.ViewHolder>() {
    val scope = CoroutineScope(Job() + Dispatchers.Default)

    inner class ViewHolder(val binding: ViewDataBinding) : BaseViewHolder<DayModel>(binding) {
        @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.O)
        override fun bind(itemData: DayModel?) {
            super.bind(itemData)
            if (binding is ItemDayMoonBinding) {
                binding.day.text = itemData?.day.toString()
                if (itemData != null) {
                    scope.launch(Dispatchers.Main) {

                        val calander = Calendar.getInstance().apply {
                            set(Calendar.YEAR, itemData.year.toInt())
                            set(Calendar.MONTH, itemData.month.toInt() - 1) // month 0-11
                            set(Calendar.DAY_OF_MONTH, itemData.day.toInt())
                        }

                        val textmoth = SimpleDateFormat("MMM").format(calander.time)
                        val textWeek = SimpleDateFormat("EEEE").format(calander.time)

                        Log.d("calander", "bind: $textmoth")
                        Log.d("calander", "bind: $textWeek")

                        val lunarDay = LunarCoreHelper.convertSolar2Lunar(
                            calander.get(Calendar.DAY_OF_MONTH),
                            calander.get(Calendar.MONTH) + 1,
                            calander.get(Calendar.YEAR),
                            Constants.TIME_ZONE
                        )

                        withContext(Dispatchers.Main) {
//                            when (lunarDay[0]) {
//                                30 -> {
//                                    binding.statusMoon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_no_moon))
//                                    binding.titleStatusMoon.text = itemView.context.getString(R.string.no_moon)
//                                }
//                                15 -> {
//                                    binding.statusMoon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_full_moon))
//                                    binding.titleStatusMoon.text = itemView.context.getString(R.string.full_moon)
//                                }
//                                else -> {
//                                    binding.statusMoon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_moon))
//                                    binding.titleStatusMoon.text = itemView.context.getString(R.string.waning_moon)
//                                }
//                            }
//                            binding.lunarCalendar.text = "${lunarDay[0]}/${lunarDay[1]}"
//                            binding.icon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_coin))
//                            binding.rank.text = textWeek
//                            binding.moth.text = textmoth


                            if (lunarDay[Constants.INDEX_0] == Constants.INDEX_15) {
                                binding.statusMoon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_full_moon))
                                itemView.context.getString(R.string.full_moon)
                                    .also { binding.titleStatusMoon.text = it }
                            } else if (lunarDay[Constants.INDEX_0] == Constants.INDEX_30) {
                                binding.statusMoon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_no_moon))
                                binding.titleStatusMoon.text =
                                    itemView.context.getString(R.string.no_moon)
                            } else {
                                binding.statusMoon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_moon))
                                binding.titleStatusMoon.text =
                                    itemView.context.getString(R.string.waning_moon)
                            }
                            binding.lunarCalendar.text =
                                lunarDay[Constants.INDEX_0].toString() + "/" + lunarDay[Constants.INDEX_1]

                            binding.icon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_coin))
                            binding.rank.text = textWeek
                            binding.moth.text = textmoth
                        }
                    }
                }
                binding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
            binding.executePendingBindings()
        }
    }

    override fun getItemLayoutResource(viewType: Int): Int {
        return R.layout.item_day_moon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(getViewHolderDataBinding(parent, viewType))
    }
}