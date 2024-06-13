package com.smartwavettn.horoscope.ui.intro.introTwo

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.local.Preferences
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.base.utils.gone
import com.smartwavettn.horoscope.base.utils.visible
import com.smartwavettn.horoscope.databinding.FragmentIntroTwoBinding
import com.smartwavettn.horoscope.model.PersonalInformation
import com.smartwavettn.horoscope.ui.home.HomeFragment
import com.smartwavettn.horoscope.ui.intro.introThreeInformation.IntroThreeFragment
import com.smartwavettn.horoscope.ui.utils.KeyWord
import com.smartwavettn.horoscope.ui.utils.PickerLayoutManager
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class IntroTwoFragment : BaseFragmentWithBinding<FragmentIntroTwoBinding>() {

    private var positionPickerLayout: Int = 1
    private var uriImage: String = ""
    private var personalInformation: PersonalInformation? = null
    private lateinit var preferences : Preferences

    private val viewModel: IntroTwoViewModel by viewModels()
    private lateinit var adapter: AvatarAdapter

    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroTwoBinding {
        return FragmentIntroTwoBinding.inflate(inflater)
    }

    override fun init() {
        context?.let { viewModel.init(it) }
        preferences = Preferences.getInstance(requireActivity())
        personalInformation = arguments?.getSerializable(KeyWord.profilePersona) as PersonalInformation?

        var type = arguments?.getString(KeyWord.checkFragment)

        when (type) {
           "home"-> {
                binding.btnContinue.gone()
                binding.btnOke.visible()

                binding.editName.setText(personalInformation?.name)
                binding.txtDateOfBirth.setText(personalInformation?.date)
            }

           "slashFragment" -> {}
        }

        val pickerLayoutManager =
            PickerLayoutManager(requireContext(), PickerLayoutManager.HORIZONTAL, false).apply {
                changeAlpha = true
                scaleDownBy = 0.99f
                scaleDownDistance = 0.8f
            }

        binding.rcvViewAvatar.layoutManager = pickerLayoutManager
        adapter = AvatarAdapter {}

        binding.rcvViewAvatar.adapter = adapter
        binding.rcvViewAvatar.setHasFixedSize(true)

        pickerLayoutManager.setOnScrollStopListener { view ->
            positionPickerLayout = binding.rcvViewAvatar.getChildAdapterPosition(view)
        }
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
                    binding.rcvViewAvatar.gone()
                    binding.imageAvatarConstraintLayout.visible()
                    Glide.with(requireActivity()).load(uri).into(binding.imageAvatar)
                    uriImage = uri.toString()
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

        binding.btnContinue.click {
            val name = binding.editName.text.trim().toString()
            val date = binding.txtDateOfBirth.text.trim().toString()

            if (name.isNotEmpty() && date.isNotEmpty() && binding.editName.error == null) {
                var personalInformation =
                    PersonalInformation(0, name, date,
                        if (binding.rcvViewAvatar.isVisible) viewModel.listAvatarResIds.get(
                            positionPickerLayout
                        ) else 0,
                        if (binding.imageAvatarConstraintLayout.isVisible) uriImage else "",
                        true
                    )
                if (viewModel.isUserExist(personalInformation))
                    showDialogEnterInFormation(personalInformation)
                else {
                    viewModel.addPersonalInformation(personalInformation)
                    preferences.firstInstall = true
                    openFragment(IntroThreeFragment::class.java, null, true)
                }
            } else {
                if (name.isEmpty() && date.isEmpty()) {
                    binding.editName.error = "not value"
                    binding.txtDateOfBirth.error = "not value"
                }
            }
        }

        binding.btnOke.click {
            personalInformation?.let { it1 -> updateFriends(it1) }
        }

        binding.aaaa.click {
            openFragment(HomeFragment::class.java, null, true)
        }
    }

    private fun updateFriends(personalInformation: PersonalInformation) {
        personalInformation?.apply {
            name = binding.editName.text.toString().trim()
            date = binding.txtDateOfBirth.text.toString().trim()

            icon = if (binding.rcvViewAvatar.isVisible) viewModel.listAvatarResIds.get(positionPickerLayout) else 0
            iconImage = if (binding.imageAvatarConstraintLayout.isVisible) uriImage else ""

            viewModel.updateFriends(this)
            onBackPressed()
        }
    }

    private fun showDialogEnterInFormation(personalInformation: PersonalInformation) {
        AlertDialog.Builder(requireActivity())
            .setTitle(R.string.duplicateNAme.toString() + " '${personalInformation?.name}'")
            .setMessage(R.string.another.toString() )
            .setNegativeButton("OK", null)
            .show()
    }
}