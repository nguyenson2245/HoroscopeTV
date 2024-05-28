package com.smartwavettn.horoscope.ui.home

import android.animation.LayoutTransition
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.viewModels
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.FragmentHomeBinding
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class HomeFragment : BaseFragmentWithBinding<FragmentHomeBinding>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun init() {

    }

    override fun initData() {
    }

    override fun initAction() {
       binding.menu.view1.layoutTransition.enableTransitionType(LayoutTransition.CHANGING);


        binding.profileHeader.menuProfileHeader.click {
            binding.drawer.openDrawer(GravityCompat.START)
        }

        binding.menu.layoutNotification.setOnClickListener {
            val v = if (binding.menu.itemNotification.visibility == View.GONE) View.VISIBLE else View.GONE
            TransitionManager.beginDelayedTransition(binding.menu.view1, AutoTransition())
            binding.menu.itemNotification.visibility = v
        }

        binding.menu.layoutLanguage.click {
            val v = if (binding.menu.itemLanguage.visibility == View.GONE) View.VISIBLE else View.GONE
            TransitionManager.beginDelayedTransition(binding.menu.view1, AutoTransition())
            binding.menu.itemLanguage.visibility = v
        }


    }



}