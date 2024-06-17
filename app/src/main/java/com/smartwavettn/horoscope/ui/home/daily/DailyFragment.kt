package com.smartwavettn.horoscope.ui.home.daily

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartwavettn.horoscope.databinding.FragmentDailyBinding
import com.smartwavettn.horoscope.ui.utils.DataJson
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
        adapter = DailyAdapter { _, _ -> }
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
