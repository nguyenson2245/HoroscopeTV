package com.smartwavettn.horoscope.ui.intro.introSevenFriends

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.FragmentIntroSevenFriendsBinding
import com.smartwavettn.horoscope.ui.intro.introEight.IntroEightFragment
import com.smartwavettn.horoscope.ui.intro.introTwo.AvatarAdapter
import com.smartwavettn.horoscope.ui.intro.introTwo.IntroTwoViewModel
import com.smartwavettn.horoscope.ui.utils.PickerLayoutManager
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding


class IntroSevenFriendsFragment : BaseFragmentWithBinding<FragmentIntroSevenFriendsBinding>() {

    private val viewModel: IntroSevenViewModel by viewModels()
    private lateinit var adapter: IntroSevenAdapter

    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroSevenFriendsBinding {
      return  FragmentIntroSevenFriendsBinding.inflate(inflater)
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
        adapter = IntroSevenAdapter{

        }

        binding.rcvViewAvatar.adapter = adapter
//        binding.rcvViewAvatar.setHasFixedSize(true)
    }

    override fun initData() {
        viewModel.initDataAvatar()
        viewModel.listAvatarLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun initAction() {
        binding.no.click {
            openFragment(IntroEightFragment::class.java,null,true)
        }

        binding.yes.click {
            openFragment(IntroEightFragment::class.java,null,true)
        }
    }

}