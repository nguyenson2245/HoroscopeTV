package com.smartwavettn.horoscope.ui.intro.introTwo

import android.app.DatePickerDialog
import android.util.Log
import android.view.LayoutInflater
import android.widget.DatePicker
import androidx.fragment.app.viewModels
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding
import com.smartwavettn.horoscope.databinding.FragmentIntroTwoBinding
import com.smartwavettn.horoscope.ui.intro.introThree_Information.IntroThreeFragment
import com.smartwavettn.horoscope.ui.utils.PickerLayoutManager
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class IntroTwoFragment : BaseFragmentWithBinding<FragmentIntroTwoBinding>() {

    private val viewModel: IntroTwoViewModel by viewModels()
    private lateinit var adapter: AvatarAdapter

    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroTwoBinding {
        return FragmentIntroTwoBinding.inflate(inflater)
    }

    override fun init() {
        val pickerLayoutManager =
            PickerLayoutManager(requireContext(), PickerLayoutManager.HORIZONTAL, false)
        pickerLayoutManager.apply {
            changeAlpha = true
            scaleDownBy = 0.99f
            scaleDownDistance = 0.8f
        }
        binding.rcvViewAvatar.layoutManager = pickerLayoutManager
        adapter = AvatarAdapter {
        }

        binding.rcvViewAvatar.adapter = adapter
        binding.rcvViewAvatar.setHasFixedSize(true)

    }

    override fun initData() {
        viewModel.initDataAvatar()
        viewModel.listAvatarLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun initAction() {
        binding.imgCalenda.click {
            context?.let { it1 ->
                viewModel.showDatePickerEnd(it1) {
                    binding.txtDateOfBirth.text = it
                }
            }
        }
        binding.btnContinue.click {
            openFragment(IntroThreeFragment::class.java,null,true)
        }

    }


}