package com.smartwavettn.horoscope.ui.home

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.local.Preferences
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.base.utils.shareApp
import com.smartwavettn.horoscope.broadcast.AlarmBroadcastReceiver
import com.smartwavettn.horoscope.databinding.FragmentHomeBinding
import com.smartwavettn.horoscope.model.PersonalInformation
import com.smartwavettn.horoscope.popup.CustomPopup
import com.smartwavettn.horoscope.ui.home.daily.DailyFragment
import com.smartwavettn.horoscope.ui.home.moth.MothFragment
import com.smartwavettn.horoscope.ui.home.year.YearFragment
import com.smartwavettn.horoscope.ui.intro.introSevenFriends.IntroSevenFriendsFragment
import com.smartwavettn.horoscope.ui.intro.introTwo.IntroTwoFragment
import com.smartwavettn.horoscope.ui.navigation.friends.FriendsFragment
import com.smartwavettn.horoscope.ui.navigation.friends.introduce.IntroduceFragment
import com.smartwavettn.horoscope.ui.navigation.friends.privacy.PrivacyPolicyFragment
import com.smartwavettn.horoscope.ui.navigation.friends.term.TermOfUseFragment
import com.smartwavettn.horoscope.ui.utils.Constants
import com.smartwavettn.horoscope.ui.utils.KeyWord
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding
import java.util.Calendar


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
    private var personalInformation: PersonalInformation? = null
    private lateinit var preferences: Preferences

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomeAdapter

    override fun getViewBinding(inflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun init() {
        context?.let { viewModel.init(it) }
        tabLayout()
        preferences = Preferences.getInstance(requireActivity())

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
        binding.menu.btnlunaDay.isChecked = preferences.getBoolean(Constants.LUNAR) ?: false
        binding.menu.btnCuttinghair.isChecked = preferences.getBoolean(Constants.CUTTING_HAIR) ?: false
        binding.menu.btnTravel.isChecked = preferences.getBoolean(Constants.TRAVEL) ?: false
        binding.menu.lunaNotification.isChecked = preferences.getBoolean(Constants.LUNAR) ?: false
        binding.menu.noAniceDayNotification.isChecked = preferences.getBoolean(Constants.DAY_NICE) ?: false
        binding.menu.abedDayNotification.isChecked = preferences.getBoolean(Constants.DAY_BAD) ?: false
        viewModel.getTime { binding.menu.timeNoti.text = it }
        viewModel.getPersonalLiveData().observe(viewLifecycleOwner) { personal ->
            if (personal != null) {
                personalInformation = personal
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
            preferences.setBoolean(Constants.LUNAR, isChecked)
            binding.calendarView.setShowLunarAndCuttingHair()

        }

        binding.menu.btnCuttinghair.setOnCheckedChangeListener { _, isChecked ->
            preferences.setBoolean(Constants.CUTTING_HAIR, isChecked)
            binding.calendarView.setShowLunarAndCuttingHair()
        }

        binding.menu.btnTravel.setOnCheckedChangeListener { _, isChecked ->
            preferences.setBoolean(Constants.TRAVEL, isChecked)
            binding.calendarView.setShowLunarAndCuttingHair()
        }

        binding.menu.lunaNotification.setOnCheckedChangeListener { _, isChecked ->
            preferences.setBoolean(Constants.LUNAR, isChecked)
            if (isChecked) setAlarmManager(200, "PushDay") else cancelAlarm(200)
        }

        binding.menu.noAniceDayNotification.setOnCheckedChangeListener { _, isChecked ->
            preferences.setBoolean(Constants.DAY_NICE, isChecked)
            if (isChecked) setAlarmManager(300, "RangeDay") else cancelAlarm(300)
        }

        binding.menu.abedDayNotification.setOnCheckedChangeListener { _, isChecked ->
            preferences.setBoolean(Constants.DAY_BAD, isChecked)
            if (isChecked) setAlarmManager(400, "RangeDay") else cancelAlarm(400)

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
                list.removeIf { it.isProfile }
                CustomPopup.showPopupMenu(
                    it1,
                    list, view
                ) {
                    val bundle = Bundle()
                    bundle.putString(KeyWord.checkFragmentFriends, KeyWord.friendsFragment)
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
                bundle.putString(KeyWord.checkFragment, KeyWord.home)
                bundle.putSerializable(KeyWord.profilePersona, personalInformation)
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


    @SuppressLint("MissingPermission")
    fun setAlarmManager(requestCode: Int, action: String) {
        val alarmManager =
            context?.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val intent = Intent(requireActivity(), AlarmBroadcastReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        intent.action = action
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager?.canScheduleExactAlarms() == false) {
                Intent().also { intent ->
                    intent.action = Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
                    context?.startActivity(intent)
                }
            } else {
                alarmManager?.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    Calendar.getInstance().timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            }
        } else {
            alarmManager?.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                Calendar.getInstance().timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
    }

    private fun cancelAlarm(requestCode: Int) {
        val alarmManager =
            context?.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val intent = Intent(requireActivity(), AlarmBroadcastReceiver::class.java)

        val pendingIntent =
            PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        alarmManager?.cancel(pendingIntent)
    }
}
