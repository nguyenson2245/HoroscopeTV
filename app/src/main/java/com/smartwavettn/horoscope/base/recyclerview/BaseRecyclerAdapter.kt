package com.smartwavettn.horoscope.base.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import androidx.recyclerview.widget.RecyclerView.Adapter

@Retention(AnnotationRetention.SOURCE)
annotation class RecyclerType
abstract class BaseRecyclerAdapter<T : Any, VH : BaseViewHolder<T>>(

) : Adapter<VH>() {
    var listItem: List<T> = arrayListOf()
    var positionSelected: Int = 0

    @LayoutRes
    abstract fun getItemLayoutResource(@RecyclerType viewType: Int): Int

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (listItem.isNotEmpty() && position < listItem.size)
        holder.bind(listItem[position])
        else holder.bind(null)
    }

    open fun submitList(list: List<T>?, isChangedAll: Boolean = true) {
        listItem = list ?: arrayListOf()
        if (isChangedAll) notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    fun getViewHolderDataBinding(parent: ViewGroup, viewType: Int): ViewDataBinding =
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), getItemLayoutResource(viewType), parent, false)
}
