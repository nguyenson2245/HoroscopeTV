package com.smartwavettn.horoscope.popup

import android.provider.ContactsContract.Profile
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.recyclerview.BaseRecyclerAdapter
import com.smartwavettn.horoscope.base.recyclerview.BaseViewHolder
import com.smartwavettn.horoscope.databinding.ItemPopupBinding
import com.smartwavettn.horoscope.model.PersonalInformation

class PopupAdapter(val onClickItem: (Int)-> Unit) : BaseRecyclerAdapter<PersonalInformation, PopupAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ViewDataBinding) : BaseViewHolder<PersonalInformation>(binding){
        override fun bind(itemData: PersonalInformation?) {
            super.bind(itemData)

            if (binding is ItemPopupBinding){
                if (itemData == null){
                    binding.imageAvatar.setImageResource(R.drawable.ic_add)
                    binding.name.text = R.string.addFriend.toString()
                }
            }
            onItemClickListener {onClickItem.invoke(adapterPosition)  }
        }
    }

    override fun getItemCount(): Int {
        return listItem.size + 1
    }

    override fun getItemLayoutResource(viewType: Int): Int {
        return R.layout.item_popup
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
    return ViewHolder(getViewHolderDataBinding(parent, viewType))
}
}