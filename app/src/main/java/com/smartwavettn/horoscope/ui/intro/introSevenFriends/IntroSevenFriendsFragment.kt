package com.smartwavettn.horoscope.ui.intro.introSevenFriends

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.base.utils.gone
import com.smartwavettn.horoscope.base.utils.visible
import com.smartwavettn.horoscope.databinding.FragmentIntroSevenFriendsBinding
import com.smartwavettn.horoscope.model.PersonalInformation
import com.smartwavettn.horoscope.ui.intro.introEight.IntroEightFragment
import com.smartwavettn.horoscope.ui.utils.PickerLayoutManager
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class IntroSevenFriendsFragment : BaseFragmentWithBinding<FragmentIntroSevenFriendsBinding>() {

    private val viewModel: IntroSevenViewModel by viewModels()
    private lateinit var adapter: IntroSevenAdapter

    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroSevenFriendsBinding {
        return FragmentIntroSevenFriendsBinding.inflate(inflater)
    }

    override fun init() {
        context?.let { viewModel.init(it) }

        val pickerLayoutManager =
            PickerLayoutManager(requireContext(), PickerLayoutManager.HORIZONTAL, false)
        pickerLayoutManager.apply {
            changeAlpha = true
            scaleDownBy = 0.99f
            scaleDownDistance = 0.8f
        }
        binding.rcvViewAvatar.layoutManager = pickerLayoutManager
        adapter = IntroSevenAdapter {

        }

        binding.rcvViewAvatar.adapter = adapter
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

        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                binding.imageAvatarConstraintLayout.visible()
                binding.rcvViewAvatar.gone()
                Glide.with(this)
                    .load(uri)
                    .circleCrop()
                    .into(binding.imageAvatar)
            } else {
                binding.imageAvatarConstraintLayout.gone()
                binding.rcvViewAvatar.visible()
            }
        }
        binding.uploadImage.click {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.imageAvatar.click {
            binding.rcvViewAvatar.visible()
            binding.imageAvatarConstraintLayout.gone()
        }

        binding.no.click {
            openFragment(IntroEightFragment::class.java, null, true)
        }

//        binding.yes.click {
//
//            val name = binding.editName.text.trim().toString()
//            val date = binding.txtDateOfBirth.text.trim().toString()
//
//            if (name.isNotEmpty() && binding.editName.error == null && date.isNotEmpty()) {
//
//                var personalInformation = PersonalInformation(0, name, date)
//
//                if (viewModel.isUserExist(personalInformation)) {
//                    AlertDialog.Builder(requireActivity())
//                        .setTitle("Duplicate name ! " + " '\n${personalInformation?.name}'")
//                        .setMessage("Change to another name : ")
//                        .setNegativeButton("OK", null)
//                        .show()
//                    return@click
//                }
//
//                context?.let { it1 -> viewModel.addPersonalInformation(it1, personalInformation) }
//
//                openFragment(IntroEightFragment::class.java, null, true)
//            } else {
//                if (name.isEmpty() && date.isEmpty()) {
//                    binding.editName.error = "not value"
//                    binding.txtDateOfBirth.error = "not value"
//                }
//            }
//
//        }
    }

}