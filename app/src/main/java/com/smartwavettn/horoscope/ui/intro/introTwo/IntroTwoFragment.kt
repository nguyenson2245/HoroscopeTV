package com.smartwavettn.horoscope.ui.intro.introTwo

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.smartwavettn.horoscope.base.utils.click
import com.smartwavettn.horoscope.base.utils.gone
import com.smartwavettn.horoscope.base.utils.visible
import com.smartwavettn.horoscope.databinding.FragmentIntroTwoBinding
import com.smartwavettn.horoscope.model.PersonalInformation
import com.smartwavettn.horoscope.ui.intro.introThreeInformation.IntroThreeFragment
import com.smartwavettn.horoscope.ui.utils.PickerLayoutManager
import com.smartwavettn.scannerqr.base.BaseFragmentWithBinding

class IntroTwoFragment : BaseFragmentWithBinding<FragmentIntroTwoBinding>() {

    private var checkFragment = true
    private var positionPickerLayout: Int = 0
    private var uriImage: String = ""

    private val viewModel: IntroTwoViewModel by viewModels()
    private lateinit var adapter: AvatarAdapter

    override fun getViewBinding(inflater: LayoutInflater): FragmentIntroTwoBinding {
        return FragmentIntroTwoBinding.inflate(inflater)
    }

    override fun init() {
        checkFragment = arguments?.getBoolean("checkFragment", true) ?: true

        context?.let { viewModel.init(it) }  // khởi tạo repository nếu dùng context

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

            Log.d("nnn", "vt-t  : ${binding.rcvViewAvatar.getChildAdapterPosition(view)}")
            Log.d("nnn", "vt-s  : $positionPickerLayout")
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

        val pickMedia =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                if (uri != null) {
                    binding.imageAvatarConstraintLayout.visible()
                    binding.rcvViewAvatar.gone()
                    Glide.with(this)
                        .load(uri)
                        .circleCrop()
                        .into(binding.imageAvatar)
                    uriImage = uri.toString()

                    Log.d("ImageUri", "1 - " + uri.toString())
                    Log.d("ImageUri", "2 - " + uriImage)

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

            if (name.isNotEmpty() && binding.editName.error == null && date.isNotEmpty()) {
                if (positionPickerLayout == -1) positionPickerLayout = 0
                var personalInformation =
                    PersonalInformation(0, name, date, positionPickerLayout, uriImage, false)

                if (viewModel.isUserExist(personalInformation)) {
                    showDialogEnterInFormation(personalInformation)
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

        binding.btnOke.click {

        }

        checkFragmentBoolean()

    }

    private fun showDialogEnterInFormation(personalInformation: PersonalInformation) {
        AlertDialog.Builder(requireActivity())
            .setTitle("Duplicate name ! " + " '${personalInformation?.name}'")
            .setMessage("Change to another name : ")
            .setNegativeButton("OK", null)
            .show()
    }

    private fun checkFragmentBoolean() {
        binding.apply {
            btnOke.visibility = if (checkFragment) View.GONE else View.VISIBLE
            btnContinue.visibility = if (checkFragment) View.VISIBLE else View.GONE
        }
    }

}