package com.smartwavettn.horoscope.ui.navigation.friends.term

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.FragmentTermOfUseBinding
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class TermOfUseFragment : BaseFragmentWithBinding<FragmentTermOfUseBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentTermOfUseBinding {
       return FragmentTermOfUseBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {
        binding.toolbar.click {
            onBackPressed()
        }
    }

}