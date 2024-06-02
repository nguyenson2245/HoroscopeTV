package com.smartwavettn.horoscope.ui.navigation.friends.privacy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.FragmentIntroduceBinding
import com.smartwavettn.horoscope.databinding.FragmentPrivacyPolicBinding
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding


class PrivacyPolicyFragment : BaseFragmentWithBinding<FragmentPrivacyPolicBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentPrivacyPolicBinding {
      return   FragmentPrivacyPolicBinding.inflate(inflater)
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