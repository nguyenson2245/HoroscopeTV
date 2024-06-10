package com.smartwavettn.horoscope.ui.home.daily

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.databinding.FragmentDailyBinding
import com.smartwavettn.horoscope.ui.home.HomeViewModel
import com.smartwavettn.horoscope.ui.intro.introSevenFriends.IntroSevenAdapter
import com.smartwavettn.horoscope.ui.utils.PickerLayoutManager
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class DailyFragment : BaseFragmentWithBinding<FragmentDailyBinding>(){

    private lateinit var adapter: DailyAdapter
    private val viewModel: DailyViewModel by viewModels()

    companion object {
        fun newInstance() = DailyFragment()
    }

    override fun getViewBinding(inflater: LayoutInflater): FragmentDailyBinding {
      return FragmentDailyBinding.inflate(inflater)
    }

    override fun init() {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = DailyAdapter()
        binding.rcvDaily.layoutManager = layoutManager
        binding.rcvDaily.adapter = adapter
        binding.rcvDaily.setHasFixedSize(true)
    }

    override fun initData() {
        viewModel.initData()

        viewModel.listAddSettings.observe(viewLifecycleOwner) {it->
            adapter.submitList(it)
        }
    }

    override fun initAction() {

    }



}