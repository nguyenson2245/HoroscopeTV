package com.smartwavettn.horoscope.ui.home

import android.animation.LayoutTransition
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.scannerqr.popup.CustomPopup
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.utils.SafeClickListener
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.base.utils.shareApp
import com.smartwavettn.horoscope.databinding.FragmentHomeBinding
import com.smartwavettn.horoscope.ui.home.daily.DailyFragment
import com.smartwavettn.horoscope.ui.home.moth.MothFragment
import com.smartwavettn.horoscope.ui.home.year.YearFragment
import com.smartwavettn.horoscope.ui.intro.introTwo.IntroTwoFragment
import com.smartwavettn.horoscope.ui.navigation.friends.FriendsFragment
import com.smartwavettn.horoscope.ui.navigation.friends.introduce.IntroduceFragment
import com.smartwavettn.horoscope.ui.navigation.friends.privacy.PrivacyPolicyFragment
import com.smartwavettn.horoscope.ui.navigation.friends.term.TermOfUseFragment
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class HomeFragment : BaseFragmentWithBinding<FragmentHomeBinding>(),(View) -> Unit   {

    private var nameAppOwner = ""
    private var dateApplicationOwner = ""

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val listFragment: ArrayList<Fragment> = arrayListOf(
        DailyFragment.newInstance(),
        MothFragment.newInstance(),
        YearFragment.newInstance()
    )

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomeAdapter

    override fun getViewBinding(inflater: LayoutInflater): FragmentHomeBinding{
        return FragmentHomeBinding.inflate(inflater)
    }


    override fun init() {
        context?.let { viewModel.init(it) }

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

                if (profilePersona.icon != 1) {
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

        binding.day.onDateListener {
            binding.calendarView.setDaySelect(it)
        }

        binding.calendarView.onClickSelected = {
            binding.day.selectDay(it.day.toInt(), it.month.toInt(), it.year.toInt(), true)
            binding.calendarView.dayModel = it
        }

        tabLayout()
    }


    override fun initData() {
        viewModel.init(requireActivity())
    }

    override fun initAction() {
        binding.profileHeader.menuProfileHeader.click {
            binding.drawer.openDrawer(GravityCompat.START)
        }

        binding.profileHeader.image.click {
            context?.let { it1 -> CustomPopup.showPopupMenu(it1, viewModel.getListData(),it) }
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
                binding.menu.timeNoti.text = it
            }
        }

        binding.menu.layoutLanguage.click {
            binding.menu.btnLanguage.setImageResource(if (binding.menu.itemLanguage.isVisible) R.drawable.soo else R.drawable.soo2)
            val v = if (binding.menu.itemLanguage.visibility == View.GONE) View.VISIBLE else View.GONE
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

        binding.menu.btnlunaDay.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                toast(" ON")
            } else {
                toast(" OFF")
            }
        }

        binding.menu.btnCuttinghair.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                toast(" ON")
            } else {
                toast(" OFF")
            }
        }

        binding.menu.btnTravel.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                toast(" ON")
            } else {
                toast(" OFF")
            }
        }

        binding.menu.lunaNotification.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                toast(" ON")

            } else {
                toast(" OFF")
            }
        }

        binding.menu.noAniceDayNotification.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                toast(" ON")
            } else {
                toast(" OFF")
            }
        }

        binding.menu.abedDayNotification.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                toast(" ON")
            } else {
                toast(" OFF")
            }
        }


    }


    private fun tabLayout() {
        adapter = HomeAdapter(
            parentFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        binding.viewPager.adapter = adapter
                adapter.setData(listFragment)

        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.viewPager.measureCurrentView(listFragment[0].view)
        binding.viewPager.viewTreeObserver.addOnGlobalLayoutListener {
            binding.viewPager.measureCurrentView(listFragment[binding.viewPager.currentItem].view)
        }
    }

    override fun invoke(view: View) {
        when (view.id){
            R.id.btnTravel -> {

            }
            R.id.linnerLayoutProfile -> {

            }
        }
    }
}