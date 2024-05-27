package com.smartwavettn.horoscope.ui.intro.introTwo

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.recyclerview.BaseRecyclerAdapter
import com.smartwavettn.horoscope.base.recyclerview.BaseViewHolder
import com.smartwavettn.horoscope.databinding.ItemAvatarBinding
import com.smartwavettn.horoscope.model.Avatar

class AvatarAdapter (val click: (Class<*>) -> Unit) : BaseRecyclerAdapter<Avatar, AvatarAdapter.AvatarViewHolder>(){

    inner class AvatarViewHolder(val binding : ViewDataBinding) : BaseViewHolder<Avatar>(binding){
        override fun bind(itemData: Avatar?) {
            super.bind(itemData)
            if (binding is ItemAvatarBinding){
                Glide.with(itemView).load(itemData?.icon).into(binding.image)
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