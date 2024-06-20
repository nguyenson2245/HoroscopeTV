package com.smartwavettn.horoscope.ui.home.year

import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.recyclerview.BaseRecyclerAdapter
import com.smartwavettn.horoscope.base.recyclerview.BaseViewHolder
import com.smartwavettn.horoscope.base.utils.gone
import com.smartwavettn.horoscope.customview.model.Year
import com.smartwavettn.horoscope.databinding.ItemYearBinding

class YearAdapter(private val click: (Year) -> Unit) :
    BaseRecyclerAdapter<Year, YearAdapter.YearViewHolder>() {


    inner class YearViewHolder(val binding: ViewDataBinding) : BaseViewHolder<Year>(binding) {
        override fun bind(itemData: Year?) {
            super.bind(itemData)
            if (binding is ItemYearBinding) {
                binding.textYear.text = itemData?.tibYear.toString()

                if (binding is ItemYearBinding) {
                    binding.textYear.text = itemData?.tibYear.toString()


                    binding.selectedIndicator.visibility =
                        if (adapterPosition == getPositionSelected()) View.VISIBLE else View.INVISIBLE
                    onItemClickListener {
                        itemData?.let { data ->
                            notifyItemChanged(getPositionSelected())
                            setPositionSelected(adapterPosition)
                            click.invoke(data)
                        }
                    }
                }
            }
        }
    }


    override fun getItemLayoutResource(viewType: Int): Int {
        return R.layout.item_year
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YearViewHolder {
        return YearViewHolder(getViewHolderDataBinding(parent, viewType))
    }


}