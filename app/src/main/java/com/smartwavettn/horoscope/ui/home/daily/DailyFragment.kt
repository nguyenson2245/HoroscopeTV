package com.smartwavettn.horoscope.ui.home.daily

import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartwavettn.horoscope.databinding.FragmentDailyBinding
import com.smartwavettn.horoscope.ui.utils.DataJson
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class DailyFragment : BaseFragmentWithBinding<FragmentDailyBinding>() {

    private lateinit var adapter: DailyAdapter
    private val viewModel: DailyViewModel by viewModels()

    companion object {
        fun newInstance() = DailyFragment()
    }

    override fun getViewBinding(inflater: LayoutInflater): FragmentDailyBinding {
        return FragmentDailyBinding.inflate(inflater)
    }

    override fun init() {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = DailyAdapter { daily, position ->
            when (position) {

                1-> {
                    val personalDayKeysList = (DataJson.getMessagePersonalDayKeys(requireActivity(),"Message-Personal-Day-")).toList().take(3)
                    for ((key, value) in personalDayKeysList) {
                        Log.d("logDataJson", "-> $key, : $value")
                    }
                }

                2 -> {
                    DataJson.getValueByKey(requireActivity(), "Message-Group-Constellations-")
                }

                4 -> {
                    DataJson.getValueByKey(requireActivity(), "Message-Big-Combo-")
                }

                5 -> {
                    //luna_day
                    DataJson.getValueByKey(requireActivity(), "Moon-Day-")
                }

                6 -> {
                    //hair
                    DataJson.getValueByKey(requireActivity(), "Haircut-Day-")
                }

                7 -> {
                    DataJson.getValueByKey(requireActivity(), "Sources-")
                }

                8 -> {
                    DataJson.getValueByKey(requireActivity(), "La_position_in_the_body")
                }

                else -> {
                    toast(position.toString())
                }
            }
        }
        binding.rcvDaily.layoutManager = layoutManager
        binding.rcvDaily.adapter = adapter
        binding.rcvDaily.setHasFixedSize(true)
    }

    override fun initData() {
        viewModel.initData()

        viewModel.listAddSettings.observe(viewLifecycleOwner) { it ->
            adapter.submitList(it)
        }
    }

    override fun initAction() {

    }


}