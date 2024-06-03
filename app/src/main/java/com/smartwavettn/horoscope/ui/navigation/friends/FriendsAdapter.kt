package com.smartwavettn.horoscope.ui.navigation.friends

import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.recyclerview.BaseRecyclerAdapter
import com.smartwavettn.horoscope.base.recyclerview.BaseViewHolder
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.ItemFriendsBinding
import com.smartwavettn.horoscope.model.PersonalInformation

class FriendsAdapter(val click: (PersonalInformation) -> Unit , val detele : (PersonalInformation) -> Unit) :
    BaseRecyclerAdapter<PersonalInformation, FriendsAdapter.FriendsViewHolder>() {

    inner class FriendsViewHolder(val binding: ViewDataBinding) :
        BaseViewHolder<PersonalInformation>(binding) {
        override fun bind(itemData: PersonalInformation?) {
            super.bind(itemData)
            if (binding is ItemFriendsBinding) {

                if (itemData != null) {
                    Glide.with(itemView.context)
                        .load(
                            when {
                                itemData?.icon != 1 -> itemData.icon
                                else -> Uri.parse(itemData?.iconImage)


                            }
                        )
                        .into(binding.image)
                }

                binding.name.text = itemData?.name

                onItemClickListener {
                    if (itemData != null) {
                        click.invoke(itemData)
                    }
                }

                binding.delete.click {
                    if (itemData != null) {
                        detele.invoke(itemData)
                    }
                }
            }
        }
    }

    override fun getItemLayoutResource(viewType: Int): Int {
        return R.layout.item_friends
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        return FriendsViewHolder(getViewHolderDataBinding(parent, viewType))
    }


}