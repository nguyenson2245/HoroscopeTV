package com.smartwavettn.horoscope.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class HomeAdapter(fragmentManager: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fragmentManager, behavior) {
    var listFragment: ArrayList<Fragment> = arrayListOf()
    override fun getCount(): Int {
        return listFragment.size
    }

    override fun getItem(position: Int): Fragment {
        return listFragment[position]
    }

    fun setData(data: ArrayList<Fragment>) {
        listFragment = data
        notifyDataSetChanged()
    }

}