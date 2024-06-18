package com.smartwavettn.horoscope.ui.home.year

import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.smartwavettn.horoscope.databinding.FragmentYearBinding
import com.smartwavettn.horoscope.model.TibYearsPerams
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

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

//            val tibYearsParams = readTibYearsParamsFromJson()
//
//            Log.d("YourFragment", "Thông tin năm Tây Tạng:")
//            Log.d("YourFragment", "Năm: ${tibYearsParams.tibYear}")
//            Log.d("YourFragment", "Tháng đầu tiên: ${tibYearsParams.tibYearFirstMonth}")
//            Log.d("YourFragment", "Ngày đầu tiên: ${tibYearsParams.tibYearFirstDay}")
    }


    override fun initData() {
        adapter = YearAdapter { year, position -> }

        binding.rvView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvView.adapter = adapter
        binding.rvView.setHasFixedSize(true)

        viewModel.initDataCreateQr()
        viewModel.listYearLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun initAction() {

    }

//        private fun readTibYearsParamsFromJson(): TibYearsPerams {
//            val inputStream = requireContext().assets.open("tib_years_perams.json")
//            val jsonString = inputStream.bufferedReader().use { it.readText() }
//
//
//            val yearMap = Gson().fromJson(jsonString, Map::class.java) as Map<String, Any>
//            return TibYearsParams.fromMap(yearMap)
//        }
//
//
//        object TibYearsParams {
//            fun fromMap(yearMap: Map<String, Any>): TibYearsPerams {
//                return TibYearsPerams(
//                    tibYear = yearMap["tibYear"] as Int,
//                    tibYearFirstMonth = getInt(yearMap, "tibYearFirstMonth", 1),
//                    tibYearFirstDay = getInt(yearMap, "tibYearFirstDay", 1),
//                    yearEl = yearMap["yearEl"] as Int,
//                    yearAnimal = yearMap["yearAnimal"] as Int,
//                    sogEl = yearMap["sogEl"] as Int,
//                    luEl = yearMap["luEl"] as Int,
//                    vangEl = yearMap["vangEl"] as Int,
//                    lungtaEl = yearMap["lungtaEl"] as Int,
//                    laEl = yearMap["laEl"] as Int,
//                    meva = yearMap["meva"] as Int,
//                    luMe = yearMap["luMe"] as Int,
//                    sogMe = yearMap["sogMe"] as Int,
//                    vanMe = yearMap["vanMe"] as Int,
//                    lunMe = yearMap["lunMe"] as Int,
//                    persDays = (yearMap["persDays"] as? List<*>)?.map { it as Int } ?: emptyList(),
//                    persStars = (yearMap["persStars"] as? List<*>)?.map { it as Int } ?: emptyList(),
//                    persMoonDays = (yearMap["persMoonDays"] as? List<*>)?.map { it as Int } ?: emptyList()
//                )
//            }
//
//            private fun getInt(map: Map<String, Any>, key: String, default: Int): Int {
//                return try {
//                    (map[key] as? String)?.toInt() ?: default
//                } catch (e: NumberFormatException) {
//                    default
//                }
//            }
//        }
}