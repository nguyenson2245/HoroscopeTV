package com.smartwavettn.horoscope.ui.intro.introTwo

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.databinding.FragmentIntroTwoBinding
import com.smartwavettn.horoscope.model.PersonalInformation
import com.smartwavettn.horoscope.ui.intro.introThreeInformation.IntroThreeFragment
import com.smartwavettn.horoscope.ui.utils.PickerLayoutManager
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class IntroTwoFragment : BaseFragmentWithBinding<FragmentIntroTwoBinding>() {

    private val viewModel: IntroTwoViewModel by viewModels()
    private lateinit var adapter: AvatarAdapter

    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroTwoBinding {
        return FragmentIntroTwoBinding.inflate(inflater)
    }

    override fun init() {
        context?.let { viewModel.init(it) }  // khởi tạo repository nếu dùng context

        val pickerLayoutManager =
            PickerLayoutManager(requireContext(), PickerLayoutManager.HORIZONTAL, false)
        pickerLayoutManager.apply {
            changeAlpha = true
            scaleDownBy = 0.99f
            scaleDownDistance = 0.8f
        }
        binding.rcvViewAvatar.layoutManager = pickerLayoutManager
        adapter = AvatarAdapter {
        }

        binding.rcvViewAvatar.adapter = adapter
        binding.rcvViewAvatar.setHasFixedSize(true)

    }

    override fun initData() {
        viewModel.initDataAvatar()
        viewModel.listAvatarLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun initAction() {

        binding.imgCalenda.click {
            context?.let { it1 ->
                viewModel.showDatePickerEnd(it1) {
                    binding.txtDateOfBirth.text = it
                }
            }
        }

        binding.btnContinue.click {
            val name = binding.editName.text.trim().toString()
            val date = binding.txtDateOfBirth.text.trim().toString()

            if (name.isNotEmpty() && binding.editName.error == null && date.isNotEmpty()) {

                var personalInformation = PersonalInformation(0, name, date)

                if (viewModel.isUserExist(personalInformation)){
                    AlertDialog.Builder(requireActivity())
                        .setTitle("Duplicate name ! " + " '${personalInformation?.name}'")
                        .setMessage("Change to another name : ")
                        .setNegativeButton("OK", null)
                        .show()
                    return@click
                }

                context?.let { it1 -> viewModel.addPersonalInformation(it1, personalInformation) }

                openFragment(IntroThreeFragment::class.java, null, true)
            } else {
                if (name.isEmpty() && date.isEmpty()) {
                    binding.editName.error = "not value"
                    binding.txtDateOfBirth.error = "not value"
                }
            }
        }


    }


}