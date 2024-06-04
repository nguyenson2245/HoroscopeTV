package com.smartwavettn.horoscope.ui.home

import android.animation.LayoutTransition
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.akexorcist.snaptimepicker.SnapTimePickerDialog
import com.bumptech.glide.Glide
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.base.utils.shareApp
import com.smartwavettn.horoscope.databinding.FragmentFriendsBinding
import com.smartwavettn.horoscope.databinding.FragmentHomeBinding
import com.smartwavettn.horoscope.model.PersonalInformation
import com.smartwavettn.horoscope.ui.intro.introSevenFriends.IntroSevenFriendsFragment
import com.smartwavettn.horoscope.ui.intro.introSevenFriends.IntroSevenViewModel
import com.smartwavettn.horoscope.ui.intro.introTwo.IntroTwoFragment
import com.smartwavettn.horoscope.ui.navigation.friends.FriendsFragment
import com.smartwavettn.horoscope.ui.navigation.friends.introduce.IntroduceFragment
import com.smartwavettn.horoscope.ui.navigation.friends.privacy.PrivacyPolicyFragment
import com.smartwavettn.horoscope.ui.navigation.friends.term.TermOfUseFragment
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding
import java.util.Calendar
import kotlin.math.log

class HomeFragment : BaseFragmentWithBinding<FragmentHomeBinding>() {

    private var nameAppOwner = ""
    private var dateApplicationOwner = ""

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getListPersonaLiveData().observe(viewLifecycleOwner) { personaList ->
            val profilePersona = personaList.find { it.isProfile }
            if (profilePersona != null) {
                nameAppOwner = profilePersona.name
                dateApplicationOwner = profilePersona.date

                with(binding) {
                    profileHeader.txtName.text = nameAppOwner
                    menu.drawerHeaderProifile.apply {
                        txtName.text = nameAppOwner
                        txtDate.text = dateApplicationOwner
                    }
                }

                if (profilePersona.icon != null && profilePersona.icon != 1) {
                    binding.menu.drawerHeaderProifile.image.setImageResource(profilePersona.icon)
                    binding.profileHeader.image.setImageResource(profilePersona.icon)
                } else if (profilePersona.iconImage.isNotEmpty()) {
                    Glide.with(this)
                        .load(profilePersona.iconImage)
                        .into(binding.menu.drawerHeaderProifile.image)
                    Glide.with(this)
                        .load(profilePersona.iconImage)
                        .into(binding.profileHeader.image)
                } else {
                    binding.menu.drawerHeaderProifile.image.setImageResource(R.drawable.intro1)
                    binding.profileHeader.image.setImageResource(R.drawable.intro1)
                }

                binding.menu.drawerHeaderProifile.linnerLayoutProfile.click {
                    val bundle = Bundle()
                    bundle.putString("checkFragment", "home")
                    bundle.putSerializable("profilePersona", profilePersona)

                    openFragment(IntroTwoFragment::class.java, bundle, true)
                    binding.drawer.closeDrawers()
                }
            }
        }
    }

    override fun init() {
        context?.let { viewModel.init(it) }
    }

    override fun initData() {

    }

    override fun initAction() {
        binding.profileHeader.menuProfileHeader.click {
            binding.drawer.openDrawer(GravityCompat.START)
        }

        binding.menu.view1.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        binding.menu.layoutNotification.click {
            binding.menu.btnNotification.setImageResource(if (binding.menu.itemNotification.isVisible) R.drawable.soo else R.drawable.soo2)
            val v = if (binding.menu.itemNotification.visibility == View.GONE) View.VISIBLE else View.GONE
            TransitionManager.beginDelayedTransition(binding.menu.view1, AutoTransition())
            binding.menu.itemNotification.visibility = v

            binding.menu.timeNoti.visibility = if (v == View.VISIBLE) View.VISIBLE else View.GONE
        }

        binding.menu.timeNoti.setOnClickListener {
            viewModel.timeApp {
                binding.menu.timeNoti.text= it
            }
        }

        binding.menu.layoutLanguage.click {

            binding.menu.btnLanguage.setImageResource(
                if (binding.menu.itemLanguage.isVisible) R.drawable.soo else R.drawable.soo2
            )
            val v =
                if (binding.menu.itemLanguage.visibility == View.GONE) View.VISIBLE else View.GONE
            TransitionManager.beginDelayedTransition(binding.menu.view1, AutoTransition())
            binding.menu.itemLanguage.visibility = v
        }

        binding.menu.friends.click {
            binding.drawer.closeDrawers()
            openFragment(FriendsFragment::class.java, null, true)
        }

        binding.menu.btnlunaDay.click {

        }

        binding.menu.btnCuttinghair.click {

        }

        binding.menu.btnTravel.click {

        }

        binding.menu.btnCuttinghair.click {

        }

        binding.menu.lunaNotification.click {

        }

        binding.menu.noAniceDayNotification.click {

        }

        binding.menu.abedDayNotification.click {

        }

        binding.menu.introduce.click {
            openFragment(IntroduceFragment::class.java, null, true)
            binding.drawer.closeDrawers()
        }

        binding.menu.share.click {
           activity?.shareApp()
            binding.drawer.closeDrawers()
        }

        binding.menu.contact.click {
            viewModel.openEmailApp()
            binding.drawer.closeDrawers()
        }

        binding.menu.privacyPolicy.click {
            openFragment(PrivacyPolicyFragment::class.java, null, true)
            binding.drawer.closeDrawers()
        }

        binding.menu.termsOfUse.click {
            openFragment(TermOfUseFragment::class.java, null, true)
            binding.drawer.closeDrawers()
        }

    }

    private fun onTimePicked(selectedHour: Int, selectedMinute: Int) {

        val hour = selectedHour.toString()
            .padStart(2, '0')
        val minute = selectedMinute.toString()
            .padStart(2, '0')
        binding.menu.timeNoti.text = String.format(
            getString(
                R.string.selected_time_format,
                hour, minute
            )
        )
    }

}