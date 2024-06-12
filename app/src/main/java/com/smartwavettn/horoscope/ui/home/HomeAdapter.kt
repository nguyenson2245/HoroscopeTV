package com.smartwavettn.horoscope.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.smartwavettn.horoscope.R

class HomeAdapter(fragmentManager: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fragmentManager) {
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

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "Daily"
            1-> "Month"
            2-> "Year"
            else->  "Daily"
        }
    }
}