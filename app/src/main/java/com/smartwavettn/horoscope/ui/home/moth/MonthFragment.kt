package com.smartwavettn.horoscope.ui.home.moth

import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.widget.GridLayout.FILL
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.customview.model.Year
import com.smartwavettn.horoscope.databinding.FragmentMonthBinding
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding
import java.util.Calendar
import kotlin.random.Random

class MothFragment : BaseFragmentWithBinding<FragmentMonthBinding>() {

    companion object {
        fun newInstance() = MothFragment()
    }

    private val viewModel: MothViewModel by viewModels()
    private lateinit var adapter: MothAdapter
    override fun getViewBinding(inflater: LayoutInflater): FragmentMonthBinding {
        return FragmentMonthBinding.inflate(inflater)
    }


    override fun init() {
        adapter = MothAdapter(){
            randomizeChartValues()
        }
        binding.rcView.adapter = adapter
        binding.rcView.layoutManager = GridLayoutManager(context, 5,GridLayoutManager.VERTICAL, false)

        randomizeChartValues()
    }

    override fun initData() {
        viewModel.initData()
        viewModel.listMothLiveData.observe(viewLifecycleOwner){
            adapter.submitList(it)

            adapter.setPositionSelected(
                it.lastIndexOf(
                    Calendar.getInstance().get(Calendar.MONTH) + 1
                )
            )
        }
    }

    override fun initAction() {

    }

    private fun chartView(values: FloatArray) {
        val axis = LinkedHashMap<String, Float>(5).apply {
            put("CA", values[0])
            put("ID", values[1])
            put("NY", values[2])
            put("NM", values[3])
            put("MN", values[4])
        }
        val chartView = binding.radarChart
        chartView.setAxis(axis)
        chartView.setAutoSize(true)
        chartView.setCirclesOnly(true)
        chartView.setListAxisColor(
            arrayListOf(
                Color.YELLOW, Color.GREEN, Color.RED, Color.BLUE, context?.resources
                    ?.getColor(R.color.purple_200)
            )
        )
        chartView.setChartStyle(Paint.Style.FILL)
        setDataYear(Year().apply {
            luEl = values[0].toInt()
            luMe = values[1].toInt()
            lungtaEl = values[2].toInt()
            meva = values[3].toInt()
            vanMe = values[4].toInt()
        })
    }

    private fun randomizeChartValues() {
        val values = FloatArray(12) {
            Random.nextFloat() * 100
        }
        chartView(values)

    }

    private fun setDataYear(year: Year) {
        binding.progressLuck.progress = year.luEl
        binding.txLuck.text = year.luEl.toString() + "%"

        binding.progressBodyEnergy.progress = year.luMe
        binding.txEnergy.text = year.luMe.toString() + "%"

        binding.progressAbility.progress = year.lungtaEl
        binding.tvAbility.text = year.lungtaEl.toString() + "%"

        binding.progressVitality.progress = year.meva
        binding.txVitality.text = year.meva.toString() + "%"

        binding.progressEnergy.progress = year.vanMe
        binding.tvEnergy.text = year.vanMe.toString() + "%"
    }
}