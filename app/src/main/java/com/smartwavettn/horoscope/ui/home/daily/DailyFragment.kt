package com.smartwavettn.horoscope.ui.home.daily

import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartwavettn.horoscope.databinding.FragmentDailyBinding
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class DailyFragment : BaseFragmentWithBinding<FragmentDailyBinding>() {

    private lateinit var adapter: DailyAdapter
    private val viewModel: DailyViewModel by activityViewModels()

    companion object {
        fun newInstance() = DailyFragment()
    }

    override fun getViewBinding(inflater: LayoutInflater): FragmentDailyBinding {
        return FragmentDailyBinding.inflate(inflater)
    }

    override fun init() {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = DailyAdapter { click_, position ->

            Log.d("position", "position: ${click_}")
            Log.d("position", "position: ${position}")

            
        }
        binding.rcvDaily.layoutManager = layoutManager
        binding.rcvDaily.adapter = adapter
        binding.rcvDaily.setHasFixedSize(true)
    }

    override fun initData() {
        viewModel.listAddSettings.observe(viewLifecycleOwner) { it ->
            adapter.submitList(it)
        }
    }

    override fun initAction() {

    }

}
