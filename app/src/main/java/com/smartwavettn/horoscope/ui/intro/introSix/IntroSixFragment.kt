package com.smartwavettn.horoscope.ui.intro.introSix

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.FragmentIntroSixBinding
import com.smartwavettn.horoscope.ui.intro.introSevenFriends.IntroSevenFriendsFragment
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding


class IntroSixFragment : BaseFragmentWithBinding<FragmentIntroSixBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroSixBinding {
        return FragmentIntroSixBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {

    }

    override fun initAction() {
        binding.noLatter.click {
            openFragment(IntroSevenFriendsFragment::class.java,null,false)
        }

        binding.yesContinue.click {
            openFragment(IntroSevenFriendsFragment::class.java,null,false)
        }

    }

}