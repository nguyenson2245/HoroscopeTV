package com.smartwavettn.horoscope.ui.home.moth

import android.content.ContentValues.TAG
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.widget.GridLayout.FILL
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.smartwavettn.horoscope.customview.model.Year
import com.smartwavettn.horoscope.databinding.FragmentMonthBinding
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding
import java.util.Calendar

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
            Log.d(TAG, "init:"+ adapter.getPositionSelected())
        }
        binding.rcView.adapter = adapter
        binding.rcView.layoutManager = GridLayoutManager(context, 5,GridLayoutManager.VERTICAL, false)

        val axis = LinkedHashMap<String, Float>(5   ).apply {
            put("CA", 2312.895F)
            put("ID", 871.640F)
            put("NY", 751.280F)
            put("NM", 661.293F)
            put("MN", 661.293F)

        }
        val chartView = binding.radarChart
        chartView.setAxis(axis)

        chartView.setAutoSize(true)              // auto balance the chart
        chartView.setCirclesOnly(true)           // if you want circles instead of polygons
        chartView.setChartStyle(Paint.Style.FILL)
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

    private fun setDataYear(year: Year) {
        binding.progressLuck.progress = year.luEl
        binding.txLuck.text = year.luEl.toString() + "%"

        binding.progressBodyEnergy.progress = year.luMe
        binding.txEnergy.text = year.luMe.toString() + "%"

        binding.progressAbility.progress = year.lungtaEl
        binding.tvAbility.text = year.lungtaEl.toString() + "%"

        binding.progressVitality.progress = year.meva
        binding.txVitality.text = year.meva.toString() + "%"
    }
}