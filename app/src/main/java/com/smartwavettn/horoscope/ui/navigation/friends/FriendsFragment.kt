package com.smartwavettn.horoscope.ui.navigation.friends

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.FragmentFriendsBinding
import com.smartwavettn.horoscope.ui.intro.introSevenFriends.IntroSevenFriendsFragment
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FriendsFragment : BaseFragmentWithBinding<FragmentFriendsBinding>() {

    private lateinit var adapterFriends: FriendsAdapter
    private val viewModel: FriendsViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater): FragmentFriendsBinding {
        return FragmentFriendsBinding.inflate(inflater)
    }

    override fun init() {
        context?.let { viewModel.init(it) }
    }

    override fun initData() {
        adapterFriends = FriendsAdapter({ personalInformation ->
            val bundle = Bundle()
            bundle.putString("checkFragmentFriends", "FriendsFragment")
            bundle.putSerializable("personalInformation",personalInformation)

            openFragment(IntroSevenFriendsFragment::class.java, bundle, true)
        },
            { personalInformation -> runBlocking { viewModel.deletePersonal(personalInformation) } })
        binding.rcvViewAvatar.apply {
            adapter = adapterFriends
        }
        loadData()
    }

    override fun initAction() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.addFriends.click {
            val bundle = Bundle()
            bundle.putString("checkFragmentFriends", "addFriends")
            openFragment(IntroSevenFriendsFragment::class.java, bundle, true)
        }
    }

    fun loadData() {
        viewModel.getListPersonaLiveData().observe(viewLifecycleOwner) {
            adapterFriends.submitList(it)
        }
    }


}