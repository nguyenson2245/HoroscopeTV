package com.smartwavettn.horoscope.ui.home.moth

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
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
}