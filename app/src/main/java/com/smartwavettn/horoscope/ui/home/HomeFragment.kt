package com.smartwavettn.horoscope.ui.home

import android.animation.LayoutTransition
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.smartwavettn.horoscope.popup.CustomPopup
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.base.utils.shareApp
import com.smartwavettn.horoscope.databinding.FragmentHomeBinding
import com.smartwavettn.horoscope.ui.MainActivity
import com.smartwavettn.horoscope.ui.home.daily.DailyFragment
import com.smartwavettn.horoscope.ui.home.moth.MothFragment
import com.smartwavettn.horoscope.ui.home.year.YearFragment
import com.smartwavettn.horoscope.ui.intro.introSevenFriends.IntroSevenFriendsFragment
import com.smartwavettn.horoscope.ui.intro.introTwo.IntroTwoFragment
import com.smartwavettn.horoscope.ui.navigation.friends.FriendsFragment
import com.smartwavettn.horoscope.ui.navigation.friends.introduce.IntroduceFragment
import com.smartwavettn.horoscope.ui.navigation.friends.privacy.PrivacyPolicyFragment
import com.smartwavettn.horoscope.ui.navigation.friends.term.TermOfUseFragment
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class HomeFragment : BaseFragmentWithBinding<FragmentHomeBinding>(), (View) -> Unit,
    View.OnClickListener {

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

    override fun getViewBinding(inflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }


    override fun init() {
        context?.let { viewModel.init(it) }
        tabLayout()


        binding.day.onDateListener {
            binding.calendarView.setDaySelect(it)
        }

        binding.calendarView.onClickSelected = {
            binding.day.selectDay(it.day.toInt(), it.month.toInt(), it.year.toInt(), true)
            binding.calendarView.dayModel = it
        }
    }


    override fun initData() {
        viewModel.init(requireActivity())
        viewModel.getPersonalLiveData()
        viewModel.personal.observe(viewLifecycleOwner) { personal ->
            if (personal != null) {
                with(binding) {
                    profileHeader.txtName.text = personal.name
                    menu.drawerHeaderProifile.apply {
                        txtName.text = personal.name
                        txtDate.text = personal.date
                    }
                }
                if (personal.icon != 0) {
                    binding.menu.drawerHeaderProifile.image.setImageResource(personal.icon)
                    binding.profileHeader.image.setImageResource(personal.icon)
                } else if (personal.iconImage.isNotEmpty()) {
                    Glide.with(this)
                        .load(personal.iconImage)
                        .into(binding.menu.drawerHeaderProifile.image)
                    Glide.with(this)
                        .load(personal.iconImage)
                        .into(binding.profileHeader.image)
                } else {
                    binding.menu.drawerHeaderProifile.image.setImageResource(R.drawable.intro1)
                    binding.profileHeader.image.setImageResource(R.drawable.intro1)
                }

            }
        }
    }

    override fun initAction() {
        binding.menu.view1.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        binding.menu.drawerHeaderProifile.linnerLayoutProfile.click(this)
        binding.profileHeader.menuProfileHeader.click(this)
        binding.profileHeader.image.click(this)
        binding.menu.layoutNotification.setOnClickListener(this)
        binding.menu.timeNoti.click(this)
        binding.menu.layoutLanguage.setOnClickListener(this)
        binding.menu.friends.click(this)
        binding.menu.btnlunaDay.click(this)
        binding.menu.btnCuttinghair.click(this)
        binding.menu.btnTravel.click(this)
        binding.menu.btnCuttinghair.click(this)
        binding.menu.lunaNotification.click(this)
        binding.menu.noAniceDayNotification.click(this)
        binding.menu.abedDayNotification.click(this)
        binding.menu.introduce.click(this)
        binding.menu.share.click(this)
        binding.menu.contact.click(this)
        binding.menu.privacyPolicy.click(this)
        binding.menu.termsOfUse.click(this)

        binding.menu.btnlunaDay.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                toast(" ON")
            } else toast(" OFF")
        }

        binding.menu.btnCuttinghair.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) toast(" ON") else toast(" OFF")
        }

        binding.menu.btnTravel.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) toast(" ON") else toast(" OFF")
        }

        binding.menu.lunaNotification.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                toast(" ON")
            } else toast(" OFF")
        }

        binding.menu.noAniceDayNotification.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                toast(" ON")
                viewModel.createNotification(requireActivity(),Intent(requireActivity(), MainActivity::class.java))

            } else toast(" OFF")
        }

        binding.menu.abedDayNotification.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) toast(" ON") else toast(" OFF")
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
        when (view.id) {
            R.id.menuProfileHeader -> binding.drawer.openDrawer(GravityCompat.START)

            R.id.image -> context?.let { it1 ->
                val list = viewModel.getListData()
                list.removeIf { !it.isProfile }
                CustomPopup.showPopupMenu(
                    it1,
                    list, view
                ) {
                    val bundle = Bundle()
                    bundle.putString("checkFragmentFriends", "FriendsFragment")
                    openFragment(IntroSevenFriendsFragment::class.java, bundle, true)
                }
            }

            R.id.friends -> openFragmentCloseDrawer(
                FriendsFragment::class.java,
                addBackStack = true
            )

            R.id.termsOfUse -> openFragmentCloseDrawer(
                TermOfUseFragment::class.java,
                addBackStack = true
            )

            R.id.privacyPolicy -> openFragmentCloseDrawer(
                PrivacyPolicyFragment::class.java,
                addBackStack = true
            )

            R.id.contact -> {
                viewModel.openEmailApp()
                binding.drawer.closeDrawers()
            }

            R.id.introduce -> openFragmentCloseDrawer(
                IntroduceFragment::class.java,
                addBackStack = true
            )


            R.id.share -> {
                activity?.shareApp()
                binding.drawer.closeDrawers()
            }

            R.id.timeNoti -> viewModel.timeApp {
                binding.menu.timeNoti.text = it
            }

            R.id.linnerLayoutProfile -> {
                val bundle = Bundle()
                bundle.putString("checkFragment", "home")
                bundle.putSerializable("profilePersona", viewModel.personal.value)
                openFragmentCloseDrawer(IntroTwoFragment::class.java, bundle, true)
            }
        }
    }

    private fun setOnCLickShowNotification() {
        binding.menu.btnNotification.setImageResource(if (binding.menu.itemNotification.isVisible) R.drawable.soo else R.drawable.soo2)
        val v =
            if (binding.menu.itemNotification.visibility == View.GONE) View.VISIBLE else View.GONE
        TransitionManager.beginDelayedTransition(binding.menu.view1, AutoTransition())
        binding.menu.itemNotification.visibility = v

        binding.menu.timeNoti.visibility = if (v == View.VISIBLE) View.VISIBLE else View.GONE
    }


    private fun openFragmentCloseDrawer(
        clazz: Class<*>,
        args: Bundle? = null,
        addBackStack: Boolean = false
    ) {
        openFragment(clazz, args, addBackStack)
        binding.drawer.closeDrawers()
    }

    private fun setOnCLickLanguages() {
        binding.menu.btnLanguage.setImageResource(
            if (binding.menu.itemLanguage.isVisible) R.drawable.soo else R.drawable.soo2
        )
        val v =
            if (binding.menu.itemLanguage.visibility == View.GONE) View.VISIBLE else View.GONE
        TransitionManager.beginDelayedTransition(binding.menu.view1, AutoTransition())
        binding.menu.itemLanguage.visibility = v
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.layoutNotification -> setOnCLickShowNotification()
            R.id.layoutLanguage -> setOnCLickLanguages()
        }
    }

}