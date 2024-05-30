package com.smartwavettn.horoscope.ui.navigation.friends

import android.os.Bundle
import android.view.LayoutInflater
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.FragmentFriendsBinding
import com.smartwavettn.horoscope.ui.intro.introSevenFriends.IntroSevenFriendsFragment
import com.smartwavettn.horoscope.ui.intro.introTwo.IntroTwoFragment
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

        binding.btnAdd.click {
            val bundle = Bundle()
            bundle.putBoolean("checkFragmentT", false)
            openFragment(IntroSevenFriendsFragment::class.java,bundle,true)
        }

    }

}