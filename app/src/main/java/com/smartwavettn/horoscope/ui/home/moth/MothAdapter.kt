package com.smartwavettn.horoscope.ui.home.moth

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.recyclerview.BaseRecyclerAdapter
import com.smartwavettn.horoscope.base.recyclerview.BaseViewHolder
import com.smartwavettn.horoscope.customview.model.Year
import com.smartwavettn.horoscope.databinding.ItemYearBinding

class MothAdapter (private val click: (Int) -> Unit): BaseRecyclerAdapter<Int, MothAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ViewDataBinding) : BaseViewHolder<Int>(binding) {
        override fun bind(itemData: Int?) {
            super.bind(itemData)
            if (binding is ItemYearBinding){
                binding.textYear.text = itemData.toString()
                binding.selectedIndicator.visibility =
                    if (adapterPosition == getPositionSelected()) View.VISIBLE else View.GONE
            }
            onItemClickListener {
                itemData?.let { data ->
                    notifyItemChanged(getPositionSelected())
                    setPositionSelected(adapterPosition)
                    click.invoke(data)
                }
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