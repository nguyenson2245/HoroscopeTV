package com.smartwavettn.horoscope.ui.intro.introTwo

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.recyclerview.BaseRecyclerAdapter
import com.smartwavettn.horoscope.base.recyclerview.BaseViewHolder
import com.smartwavettn.horoscope.databinding.ItemAvatarBinding

class AvatarAdapter (val click: (Class<*>) -> Unit) : BaseRecyclerAdapter<Int, AvatarAdapter.AvatarViewHolder>(){

    inner class AvatarViewHolder(val binding : ViewDataBinding) : BaseViewHolder<Int>(binding){
        override fun bind(itemData: Int?) {
            super.bind(itemData)
            if (binding is ItemAvatarBinding){
                Glide.with(itemView).load(itemData).into(binding.image)
                binding
            }
        }
    }

    override fun getItemLayoutResource(viewType: Int): Int {
        return R.layout.item_avatar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
      return  AvatarViewHolder(getViewHolderDataBinding(parent, viewType))
    }

}