package com.smartwavettn.horoscope.ui.home.year

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.smartwavettn.horoscope.customview.model.Year
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
            setDataYear(adapter.listItem[adapter.getPositionSelected()])
        }
        binding.rvView.adapter = adapter
    }

    override fun initData() {
        viewModel.initDataCreateQr()
        viewModel.listYearLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            if (it.size> 0){
                val item = it.last {item ->  item.tibYear == Calendar.getInstance().get(Calendar.YEAR) }
                setDataYear(item)
            }
        }
    }

    fun setDataYear(year: Year) {
        binding.progressLuck.progress = year.luEl
        binding.txLuck.text = year.luEl.toString() + "%"

        binding.progressBodyEnergy.progress = year.luMe
        binding.txEnergy.text = year.luMe.toString() + "%"

        binding.progressAbility.progress = year.lungtaEl
        binding.tvAbility.text = year.lungtaEl.toString() + "%"

        binding.progressVitality.progress = year.meva
        binding.txVitality.text = year.meva.toString() + "%"
    }

    override fun initAction() {

    }


}