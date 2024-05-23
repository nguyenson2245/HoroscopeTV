package com.example.scanqr.ui.splash

import androidx.fragment.app.viewModels
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.example.horoscope.base.local.Preferences
import com.example.horoscope.databinding.FragmentSplashBinding

import com.example.scannerqr.base.BaseFragmentWithBinding

class SplashFragment : BaseFragmentWithBinding<FragmentSplashBinding>() {

    companion object {
        fun newInstance() = SplashFragment()
    }
    private lateinit var preferences : Preferences

    private val viewModel: SplashViewModel by viewModels()


    override fun getViewBinding(inflater: LayoutInflater): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater)
    }

    override fun init() {
        preferences = Preferences.getInstance(requireActivity())
        Handler(Looper.getMainLooper()).postDelayed({

        }, 2000)
    }

    override fun initData() {

    }

    override fun initAction() {

    }
}