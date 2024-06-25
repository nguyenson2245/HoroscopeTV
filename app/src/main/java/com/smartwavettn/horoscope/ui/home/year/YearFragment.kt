package com.smartwavettn.horoscope.ui.home.year

import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.customview.model.Year
import com.smartwavettn.horoscope.databinding.FragmentYearBinding
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding
import java.util.Calendar
import kotlin.math.log

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
            binding.tvYear.text = "Year : " + year.tibYear.toString()
            val chineseZodiac = getChineseZodiacForYear(year.tibYear)
            binding.calendarCa.text = "Zodiac  : " + chineseZodiac.toString()
            chartView(
                year.luEl.toFloat(),
                year.luMe.toFloat(),
                year.lungtaEl.toFloat(),
                year.meva.toFloat(),
                year.vanMe.toFloat(),
            )
        }


        binding.nextLeft.setOnClickListener {
            val a = adapter.getPositionSelected()
            if (a > 0) {
                adapter.setPositionSelected(a - 1)
                adapter.notifyItemChanged(a)
                setDataYear(adapter.listItem[adapter.getPositionSelected()])
                binding.rvView.scrollToPosition(a - 1)
                val year = adapter.listItem.get(adapter.getPositionSelected())
                chartView(
                    year.luEl.toFloat(),
                    year.luMe.toFloat(),
                    year.lungtaEl.toFloat(),
                    year.meva.toFloat(),
                    year.vanMe.toFloat(),
                )
            }
        }

        binding.nextRight.click {
            val a = adapter.getPositionSelected()
            if (a < adapter.listItem.size - 1) {
                adapter.setPositionSelected(a + 1)
                adapter.notifyItemChanged(a)
                setDataYear(adapter.listItem[adapter.getPositionSelected()])
                binding.rvView.scrollToPosition(a + 1)
                val year = adapter.listItem.get(adapter.getPositionSelected())
                chartView(
                    year.luEl.toFloat(),
                    year.luMe.toFloat(),
                    year.lungtaEl.toFloat(),
                    year.meva.toFloat(),
                    year.vanMe.toFloat(),
                )
            }
        }

        binding.rvView.adapter = adapter
    }

    override fun initData() {
        viewModel.initDataYear()
        viewModel.listYearLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            val position =
                it.lastIndexOf(it.last { it.tibYear == Calendar.getInstance().get(Calendar.YEAR) })
            binding.rvView.scrollToPosition(position)
            adapter.setPositionSelected(position)
            if (it.size > 0) {
                val item =
                    it.last { item ->
                        item.tibYear == Calendar.getInstance().get(Calendar.YEAR)
                    }
                setDataYear(item)
                binding.tvYear.text = "Year : " + item.tibYear.toString()
                binding.calendarCa.text = "Zodiac  : " + getChineseZodiacForYear(item.tibYear)

                chartView(
                    item.luEl.toFloat(),
                    item.luMe.toFloat(),
                    item.lungtaEl.toFloat(),
                    item.meva.toFloat(),
                    item.vanMe.toFloat(),
                )
            }
        }
    }

    private fun chartView(a: Float, b: Float, c: Float, d: Float, e: Float) {
        val axis = LinkedHashMap<String, Float>(5).apply {
            put("CA", a)
            put("ID", b)
            put("NY", c)
            put("NM", d)
            put("MN", e)
        }
        val chartView = binding.radarChart
        chartView.setAxis(axis)
        chartView.setAutoSize(true)
        chartView.setCirclesOnly(true)
        chartView.setListAxisColor(
            arrayListOf(
                Color.YELLOW, Color.GREEN, Color.RED, Color.BLUE, context?.resources
                    ?.getColor(
                        R.color.purple_200
                    )
            )
        )
        chartView.setChartStyle(Paint.Style.FILL)
    }

    private fun setDataYear(year: Year) {

        binding.progressLuck.progress = year.luEl
        binding.progressBodyEnergy.progress = year.luMe
        binding.progressAbility.progress = year.lungtaEl
        binding.progressVitality.progress = year.meva
        binding.progressEnergy.progress = year.vanMe

        binding.txLuck.text = year.luEl.toString() + "%"
        binding.txEnergy.text = year.luMe.toString() + "%"
        binding.tvAbility.text = year.lungtaEl.toString() + "%"
        binding.txVitality.text = year.meva.toString() + "%"
        binding.tvEnergy.text = year.vanMe.toString() + "%"

    }

    override fun initAction() {

    }


    private fun getChineseZodiacForYear(year: Int): String {

        val zodiacAnimals = arrayOf(
            "Rat", "Ox", "Tiger", "Rabbit", "Dragon", "Snake",
            "Horse", "Goat", "Monkey", "Rooster", "Dog", "Pig"
        )

        val yearInCycle = (year - 1984) % 60
        val zodiacIndex = yearInCycle % 12
        return "${zodiacAnimals[zodiacIndex]}"
    }
}

