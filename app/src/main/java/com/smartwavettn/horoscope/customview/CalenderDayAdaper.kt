package com.smartwavettn.horoscope.customview

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.smartwavettn.horoscope.customview.model.DayModel
import com.smartwavettn.horoscope.base.recyclerview.BaseRecyclerAdapter
import com.smartwavettn.horoscope.base.recyclerview.BaseViewHolder
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.customview.model.MothModel
import com.smartwavettn.horoscope.databinding.ItemDayMoonBinding

class CalenderDayAdapter : BaseRecyclerAdapter<DayModel, CalenderDayAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ViewDataBinding) : BaseViewHolder<DayModel>(binding){
        override fun bind(itemData: DayModel?) {
            super.bind(itemData)
            if (binding is ItemDayMoonBinding){
                binding.day.text = itemData?.day.toString()
               binding.root.layoutParams =  ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
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