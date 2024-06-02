package com.smartwavettn.horoscope.ui.navigation.friends.introduce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.FragmentIntroduceBinding
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding


class IntroduceFragment : BaseFragmentWithBinding<FragmentIntroduceBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroduceBinding {
      return   FragmentIntroduceBinding.inflate(inflater)
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