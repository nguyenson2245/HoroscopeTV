package com.smartwavettn.horoscope.ui.intro.introTenEnd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.FragmentIntroTenEndBinding
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding


class IntroTenEndFragment : BaseFragmentWithBinding<FragmentIntroTenEndBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroTenEndBinding {
      return FragmentIntroTenEndBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {
        binding.btnContinue.click {

        }
    }

}