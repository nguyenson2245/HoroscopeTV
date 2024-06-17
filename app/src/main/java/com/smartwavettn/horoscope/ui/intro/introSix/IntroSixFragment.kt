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
import com.smartwavettn.horoscope.ui.utils.KeyWord
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
            val bundle = Bundle()
            bundle.putString(KeyWord.checkFragmentFriends, KeyWord.addFriendsIntro)
            openFragment(IntroSevenFriendsFragment::class.java, bundle, false)           }

        binding.yesContinue.click {
            val bundle = Bundle()
            bundle.putString(KeyWord.checkFragmentFriends, KeyWord.addFriendsIntro)
            openFragment(IntroSevenFriendsFragment::class.java, bundle, false)        }

    }

}