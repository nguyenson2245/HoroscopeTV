package com.smartwavettn.horoscope.ui.home.moth

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.recyclerview.BaseRecyclerAdapter
import com.smartwavettn.horoscope.base.recyclerview.BaseViewHolder
import com.smartwavettn.horoscope.databinding.ItemYearBinding

class MothAdapter (val onClick:()-> Unit): BaseRecyclerAdapter<Int, MothAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ViewDataBinding) : BaseViewHolder<Int>(binding) {
        override fun bind(itemData: Int?) {
            super.bind(itemData)
            if (binding is ItemYearBinding){
                binding.textYear.text = itemData.toString()
            }
            onItemClickListener {
                onClick.invoke()
                positionSelected = adapterPosition
                onClick.invoke()
            }
        }

    }

    override fun getItemLayoutResource(viewType: Int): Int {
        return R.layout.item_year
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(getViewHolderDataBinding(parent, viewType))
    }
}