package com.example.horoscope.customview

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.example.horoscope.customview.model.DayModel
import com.example.socialmedia.base.recyclerview.BaseRecyclerAdapter
import com.example.socialmedia.base.recyclerview.BaseViewHolder
import com.smartwavettn.horoscope.R

class CalenderDayAdapter : BaseRecyclerAdapter<DayModel, CalenderDayAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ViewDataBinding) : BaseViewHolder<DayModel>(binding)

    override fun getItemLayoutResource(viewType: Int): Int {
        return R.layout.item_day_moon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(getViewHolderDataBinding(parent, viewType))
    }
}