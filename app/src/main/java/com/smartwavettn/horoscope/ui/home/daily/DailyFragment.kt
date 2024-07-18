package com.smartwavettn.horoscope.ui.home.daily

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.local.Preferences
import com.smartwavettn.horoscope.databinding.FragmentDailyBinding
import com.smartwavettn.horoscope.ui.home.dialogSave.DialogSaveSelectApplication
import com.smartwavettn.horoscope.ui.inapp.PurchaseActivity
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class DailyFragment : BaseFragmentWithBinding<FragmentDailyBinding>() {

    private lateinit var adapter: DailyAdapter
    private val viewModel: DailyViewModel by activityViewModels()

    private var currentCoin = 0
    private lateinit var preferences: Preferences

    companion object {
        fun newInstance() = DailyFragment()
    }

    override fun getViewBinding(inflater: LayoutInflater): FragmentDailyBinding {
        return FragmentDailyBinding.inflate(inflater)
    }

    override fun init() {
        preferences = Preferences.getInstance(requireContext())!!
       val coin = preferences.getValueCoin().toString()

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = DailyAdapter({ click_, position -> }, { val dialog = DialogSaveSelectApplication(requireContext(), getString(R.string.anser_grant_permission) + "\n" + getString(R.string.goto_setting_and_grant_permission))

            dialog.setPositiveButtonClickListenerApplication {
                val alertDialog = AlertDialog.Builder(context)
                alertDialog.apply {
                    setTitle("Purchase confirmation")
                    setMessage("Would you like to pay 1 gold to use this feature ?")
                    setPositiveButton(
                        "Yes"
                    ) { dialogInterface, which ->
                        if (currentCoin >= 1) {
                            currentCoin -= 1
                            val coin = currentCoin.toString()
                            preferences.setValueCoin(currentCoin)



                            viewModel.openLock(it)
                        } else {
                            toast("You do not have enough gold to perform this feature !")
                            startActivity(Intent(context, PurchaseActivity::class.java))
                        }
                    }
                    setNegativeButton(
                        "No"
                    ) { dialog, which ->
                        dialog.dismiss()
                    }
                }

                val dialog = alertDialog.create()
                dialog.show()
            }


            dialog.setNegativeButtonClickListenerApplication {}

            dialog.show()

        })


        binding.rcvDaily.layoutManager = layoutManager
        binding.rcvDaily.adapter = adapter
        binding.rcvDaily.setHasFixedSize(true)
    }

    override fun initData() {
        viewModel.listAddSettings.observe(viewLifecycleOwner) { it ->
            adapter.submitList(it)
        }
    }

    override fun initAction() {

    }


}
