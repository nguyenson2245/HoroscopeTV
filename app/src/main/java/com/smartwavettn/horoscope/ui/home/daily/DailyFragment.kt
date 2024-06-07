package com.smartwavettn.horoscope.ui.home.daily

import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.databinding.FragmentDailyBinding
import com.smartwavettn.horoscope.ui.intro.introSevenFriends.IntroSevenAdapter
import com.smartwavettn.horoscope.ui.utils.PickerLayoutManager
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class DailyFragment : BaseFragmentWithBinding<FragmentDailyBinding>(){
    companion object {
        fun newInstance() = DailyFragment()
    }

    override fun getViewBinding(inflater: LayoutInflater): FragmentDailyBinding {
      return FragmentDailyBinding.inflate(inflater)
    }

    override fun init() {
        setFakeData()
    }

    override fun initData() {

    }

    override fun initAction() {

    }

    private fun setFakeData(){
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val dailyAdapter = DailyAdapter{}
        binding.listDaily.layoutManager = layoutManager
        binding.listDaily.adapter = dailyAdapter
        binding.listDaily.setHasFixedSize(true)

        val listFake = mutableListOf<Int>()
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        listFake.add(R.drawable.avatar1)
        dailyAdapter.submitList(listFake)
    }

}