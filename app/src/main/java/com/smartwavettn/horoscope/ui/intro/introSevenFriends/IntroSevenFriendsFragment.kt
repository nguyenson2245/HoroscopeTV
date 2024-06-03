package com.smartwavettn.horoscope.ui.intro.introSevenFriends

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
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

    var name = ""
    var date = ""
    private var positionPickerLayout: Int = 0
    private var uriImage: String = ""
    private var personalInformation: PersonalInformation? = null

    private val viewModel: IntroSevenViewModel by viewModels()

    private lateinit var adapter: IntroSevenAdapter

    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroSevenFriendsBinding {
        return FragmentIntroSevenFriendsBinding.inflate(inflater)
    }

    override fun init() {
        context?.let { viewModel.init(it) }

        var type = arguments?.getString("checkFragmentFriends")
        when (type) {
            "addFriends" -> {
                binding.no.gone()
                binding.update.gone()
                binding.yes.visible()
            }

            "FriendsFragment" -> {
                binding.no.gone()
                binding.yes.gone()
                binding.update.visible()
            }
        }

        personalInformation =
            arguments?.getSerializable("personalInformation") as PersonalInformation?
    }

    override fun initData() {

        personalInformation.let { personalInformation ->
            if (personalInformation is PersonalInformation) {
                name = personalInformation.name
                date = personalInformation.date

                binding.editName.setText(name)
                binding.txtDateOfBirth.setText(date)
            }
        }

        val pickerLayoutManager =
            PickerLayoutManager(requireContext(), PickerLayoutManager.HORIZONTAL, false).apply {
                changeAlpha = true
                scaleDownBy = 0.99f
                scaleDownDistance = 0.8f
            }

        binding.rcvViewAvatar.layoutManager = pickerLayoutManager
        adapter = IntroSevenAdapter {}

        binding.rcvViewAvatar.adapter = adapter
        binding.rcvViewAvatar.setHasFixedSize(true)

        pickerLayoutManager.setOnScrollStopListener { view ->
            positionPickerLayout = binding.rcvViewAvatar.getChildAdapterPosition(view)
        }

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

        binding.no.click {
            openFragment(IntroEightFragment::class.java, null, true)
        }

        binding.yes.click {
            addDataFriends()
        }

        binding.update.click {
            personalInformation?.let { it1 -> updateFriends(it1) }
        }
    }

    private fun updateFriends(personalInformation: PersonalInformation) {
        personalInformation?.apply {
            name = binding.editName.text.toString().trim()
            date = binding.txtDateOfBirth.text.toString().trim()

            icon= if (binding.rcvViewAvatar.isVisible) viewModel.listAvatarResIds.get(positionPickerLayout) else 0
            iconImage =if (binding.imageAvatarConstraintLayout.isVisible) uriImage else ""

            viewModel.updateFriends(this)
            onBackPressed()
        }
    }

    private fun addDataFriends() {
        val name = binding.editName.text.trim().toString()
        val date = binding.txtDateOfBirth.text.trim().toString()

        if (name.isNotEmpty() && date.isNotEmpty() && binding.editName.error == null) {
            var personalInformation =
                PersonalInformation(
                    0,
                    name,
                    date,
                    if (binding.rcvViewAvatar.isVisible) viewModel.listAvatarResIds.get(
                        positionPickerLayout
                    ) else 0,
                    if (binding.imageAvatarConstraintLayout.isVisible) uriImage else "",
                    false
                )
            if (viewModel.isUserExist(personalInformation))
                showDialogEnterInFormation(personalInformation)
            else {
                viewModel.addPersonalInformation(personalInformation)
                setTextView()
                toast("Add Friends - Success")
            }
        } else {
            if (name.isEmpty() && date.isEmpty()) {
                binding.editName.error = "not value"
                binding.txtDateOfBirth.error = "not value"
            }
        }
    }

    private fun setTextView() {
        with(binding) {
            editName.setText("")
            txtDateOfBirth.text = ""
            editName.hint = "Name :"
            txtDateOfBirth.hint = "Date of birth"
        }
    }

    private fun showDialogEnterInFormation(personalInformation: PersonalInformation) {
        AlertDialog.Builder(requireActivity())
            .setTitle("Duplicate name ! " + " '${personalInformation?.name}'")
            .setMessage("Change to another name : ")
            .setNegativeButton("OK", null)
            .show()
    }

}