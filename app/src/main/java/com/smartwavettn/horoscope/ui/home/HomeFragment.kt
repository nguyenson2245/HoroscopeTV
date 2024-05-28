package com.smartwavettn.horoscope.ui.home

import android.content.ContentValues
import android.util.Log
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding
import com.smartwavettn.horoscope.databinding.FragmentHomeBinding
import org.joda.time.DateTime
import org.joda.time.chrono.BuddhistChronology

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
        setupNavigationMenu()

        binding.profileHeader.menuProfileHeader.click {
            binding.drawer.openDrawer(GravityCompat.START)
//            mBinding.DrawerLayout.closeDrawer(GravityCompat.START)

        }
    }

    private fun setupNavigationMenu() {

    }

}