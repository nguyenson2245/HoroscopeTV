package com.smartwavettn.horoscope.ui.home.year

import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.smartwavettn.horoscope.databinding.FragmentYearBinding
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding
import java.util.Calendar

class YearFragment : BaseFragmentWithBinding<FragmentYearBinding>() {

    companion object {
        fun newInstance() = YearFragment()
    }

    private val viewModel: YearViewModel by viewModels()
    private lateinit var adapter: YearAdapter

    override fun getViewBinding(inflater: LayoutInflater): FragmentYearBinding {
        return FragmentYearBinding.inflate(inflater)
    }

    override fun init() {
        context?.let { viewModel.init(it) }
        adapter = YearAdapter { year ->

            Log.d("year", "year: $year")
            val tibYear = year.tibYear
            Log.d("year", "year: $tibYear")

            Log.d("ItemYearBindingdd", "init : ${adapter.getPositionSelected()}")
        }
        binding.rvView.adapter = adapter
    }

    override fun initData() {
        viewModel.initDataCreateQr()
        viewModel.listYearLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun initAction() {
//        val yearMap = DataJson.readJsonYear(requireContext())
//
//        for ((year, yearData) in yearMap) {
//            Log.d("MyFragmentYear", "Year: \n$year")
//
//            val tibYear = yearData.get("tibYear").asInt
//            val tibYearFirstMonth = yearData.get("tibYearFirstMonth").asInt
//            val tibYearFirstDay = yearData.get("tibYearFirstDay").asInt
//            val yearEl = yearData.get("yearEl").asInt
//            val yearAnimal = yearData.get("yearAnimal").asInt
//        }
    }



}