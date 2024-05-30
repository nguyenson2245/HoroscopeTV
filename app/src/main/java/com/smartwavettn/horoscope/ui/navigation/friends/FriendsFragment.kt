package com.smartwavettn.horoscope.ui.navigation.friends

import android.view.LayoutInflater
import com.smartwavettn.horoscope.databinding.FragmentFriendsBinding
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class FriendsFragment : BaseFragmentWithBinding<FragmentFriendsBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentFriendsBinding {
        return FragmentFriendsBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

}