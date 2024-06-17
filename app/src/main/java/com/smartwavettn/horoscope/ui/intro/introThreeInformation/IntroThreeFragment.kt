package com.smartwavettn.horoscope.ui.intro.introThreeInformation

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.FragmentIntroThreeBinding
import com.smartwavettn.horoscope.model.PersonalInformation
import com.smartwavettn.horoscope.ui.home.HomeViewModel
import com.smartwavettn.horoscope.ui.intro.introFourTopic.IntroFourTopicFragment
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding


class IntroThreeFragment : BaseFragmentWithBinding<FragmentIntroThreeBinding>() {

    private val viewModel: IntroThreeViewModel by viewModels()
    private var personalInformation: PersonalInformation? = null

    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroThreeBinding {
        return FragmentIntroThreeBinding.inflate(inflater)
    }

    override fun init() {
        context?.let { viewModel.init(it) }
        viewModel.init(requireActivity())
    }

    override fun initData() {
        viewModel.getPersonalLiveData().observe(viewLifecycleOwner) { personal ->
            if (personal != null) {
                personalInformation = personal
                with(binding) {
                    txtName.text = personal.name
                    txtDate.text = personal.date
                    txt.text= personal.date
                }
            }
        }
    }

    override fun initAction() {
        binding.btnContinue.click {
            openFragment(IntroFourTopicFragment::class.java, null, true)
        }
    }
}