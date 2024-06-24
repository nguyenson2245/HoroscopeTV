package com.smartwavettn.horoscope.ui.home.year

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.smartwavettn.horoscope.base.utils.click
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

        adapter = YearAdapter { _ ->
            setDataYear(adapter.listItem[adapter.getPositionSelected()])
        }

        binding.nextLeft.setOnClickListener {
            val a = adapter.getPositionSelected()
            if (a > 0) {
                adapter.setPositionSelected(a-1)
                adapter.notifyItemChanged(a)
                setDataYear(adapter.listItem[adapter.getPositionSelected()])
                binding.rvView.scrollToPosition(a - 1)
            }
        }

        binding.nextRight.click {
            val a = adapter.getPositionSelected()
            if (a < adapter.listItem.size - 1) {
                adapter.setPositionSelected(a+ 1)
                adapter.notifyItemChanged(a)
                setDataYear(adapter.listItem[adapter.getPositionSelected()])
                binding.rvView.scrollToPosition(a + 1)
            }
        }

        binding.rvView.adapter = adapter
    }

    override fun initData() {
        viewModel.initDataYear()

        viewModel.listYearLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            val position = it.lastIndexOf(it.last { it.tibYear == Calendar.getInstance().get(Calendar.YEAR) })
            binding.rvView.scrollToPosition(position)
            adapter.setPositionSelected(position)
            if (it.size > 0) {
                val item =
                    it.last { item ->
                        item.tibYear == Calendar.getInstance().get(Calendar.YEAR)
                    }
                setDataYear(item)
            }
        }
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

    override fun initAction() {

    }


}

