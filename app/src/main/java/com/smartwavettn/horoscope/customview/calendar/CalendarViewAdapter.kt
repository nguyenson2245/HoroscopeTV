package com.smartwavettn.horoscope.customview.calendar

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.recyclerview.BaseRecyclerAdapter
import com.smartwavettn.horoscope.base.recyclerview.BaseViewHolder
import com.smartwavettn.horoscope.customview.model.MothModel
import com.smartwavettn.horoscope.databinding.ItemCalendarViewBinding

class CalendarViewAdapter: BaseRecyclerAdapter<MothModel,CalendarViewAdapter.ViewHolder>() {
    inner class ViewHolder (val binding: ViewDataBinding) : BaseViewHolder<MothModel>(binding){
        override fun bind(itemData: MothModel?) {
            super.bind(itemData)
            if (binding is ItemCalendarViewBinding){
                binding.itemCalendar.setMonthAndYear(itemData?.month?.toIntOrNull(), itemData?.year?.toIntOrNull())
            }
            binding.executePendingBindings()
        }
    }


    override fun getItemLayoutResource(viewType: Int): Int {
        return R.layout.item_calendar_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(getViewHolderDataBinding(parent, viewType))
    }
}