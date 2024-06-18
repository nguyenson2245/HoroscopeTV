package com.smartwavettn.horoscope.ui.home.daily

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.recyclerview.BaseRecyclerAdapter
import com.smartwavettn.horoscope.base.recyclerview.BaseViewHolder
import com.smartwavettn.horoscope.databinding.ItemDailyBinding
import com.smartwavettn.horoscope.databinding.ItemTitleDailyBinding
import com.smartwavettn.horoscope.model.Daily
import kotlin.random.Random

class DailyAdapter( private val click: (Daily, Int) -> Unit) : BaseRecyclerAdapter<Daily, DailyAdapter.DailyViewHolder>() {

    inner class DailyViewHolder(val binding: ViewDataBinding) : BaseViewHolder<Daily>(binding) {
        override fun bind(itemData: Daily?) {
            super.bind(itemData)

            if (binding is ItemTitleDailyBinding) {
                binding.title.text = itemData?.title
            }

            if (binding is ItemDailyBinding) {
                binding.title.text = itemData?.title
                binding.content.text = itemData?.content
                Glide.with(itemView).load(itemData?.icon).into(binding.image)

                val goldStarList = listOf(R.drawable.sv, R.drawable.sv, R.drawable.sv, R.drawable.sv, R.drawable.sv)
                val whiteStarList = listOf(R.drawable.st, R.drawable.st, R.drawable.st, R.drawable.st, R.drawable.st)

                binding.rate.setImageResource(if (Random.nextInt(2) == 0) goldStarList[0] else whiteStarList[0])
                binding.rate1.setImageResource(if (Random.nextInt(2) == 0) goldStarList[1] else whiteStarList[1])
                binding.rate2.setImageResource(if (Random.nextInt(2) == 0) goldStarList[2] else whiteStarList[2])
                binding.rate3.setImageResource(if (Random.nextInt(2) == 0) goldStarList[3] else whiteStarList[3])
                binding.rate4.setImageResource(if (Random.nextInt(2) == 0) goldStarList[4] else whiteStarList[4])
            }

            onItemClickListener {position
                itemData?.let { data ->
                    click.invoke(data,position)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = listItem[position]
        return if (item.content.isEmpty())
            TYPE_TITLE
         else
            TYPE_TITLE_CONTENT
    }

    override fun getItemLayoutResource(position: Int): Int {
        return when (position) {
            TYPE_TITLE -> R.layout.item_title_daily
            else -> {
                return R.layout.item_daily
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        return DailyViewHolder(getViewHolderDataBinding(parent, viewType))
    }

    companion object {
        const val TYPE_TITLE = 0
        const val TYPE_TITLE_CONTENT = 1
    }


}