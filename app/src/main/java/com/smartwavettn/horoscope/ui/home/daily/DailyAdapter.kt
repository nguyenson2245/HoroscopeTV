package com.smartwavettn.horoscope.ui.home.daily

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.recyclerview.BaseRecyclerAdapter
import com.smartwavettn.horoscope.base.recyclerview.BaseViewHolder
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.base.utils.gone
import com.smartwavettn.horoscope.base.utils.visible
import com.smartwavettn.horoscope.databinding.ItemDailyBinding
import com.smartwavettn.horoscope.databinding.ItemTitleDailyBinding
import com.smartwavettn.horoscope.model.Daily
import kotlin.random.Random

class DailyAdapter( private val click: (Daily, Int) -> Unit, val clickOpenLock:(Int)-> Unit) : BaseRecyclerAdapter<Daily, DailyAdapter.DailyViewHolder>() {

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

                val totalStars = 5
                val goldStars = Random.Default.nextInt(totalStars + 1)
                val whiteStars = totalStars - goldStars

                val starList = mutableListOf<Int>()

                repeat(goldStars) { starList.add(R.drawable.sv) }
                repeat(whiteStars) { starList.add(R.drawable.st) }

                binding.rate.setImageResource(starList[0])
                binding.rate1.setImageResource(starList[1])
                binding.rate2.setImageResource(starList[2])
                binding.rate3.setImageResource(starList[3])
                binding.rate4.setImageResource(starList[4])

                if (itemData?.lock == true)
                    binding.view.visible()
                else binding.view.gone()
                binding.view.click {
                    clickOpenLock.invoke(adapterPosition)
                }
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