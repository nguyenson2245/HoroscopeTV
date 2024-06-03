package com.smartwavettn.horoscope.customview.calendar

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.recyclerview.BaseRecyclerAdapter
import com.smartwavettn.horoscope.base.recyclerview.BaseViewHolder
import com.smartwavettn.horoscope.base.utils.gone
import com.smartwavettn.horoscope.base.utils.visible
import com.smartwavettn.horoscope.customview.model.DayModel
import com.smartwavettn.horoscope.databinding.ItemDayBinding

class WeekCalendarAdapter : BaseRecyclerAdapter<DayModel, WeekCalendarAdapter.ViewHolder>() {
    inner  class ViewHolder(val binding : ViewDataBinding) : BaseViewHolder<DayModel>(binding){
        override fun bind(itemData: DayModel?) {
            super.bind(itemData)
            if (binding is ItemDayBinding){
                    binding.title.text = itemData?.day
                    if (itemData?.day.isNullOrEmpty()){
                        binding.viewLayout.gone()
                        binding.underline.gone()
                    }else{
                        binding.viewLayout.visible()
                        binding.underline.visible()
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