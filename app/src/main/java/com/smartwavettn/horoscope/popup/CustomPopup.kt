package com.smartwavettn.horoscope.popup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.smartwavettn.horoscope.base.utils.dpToPx
import com.smartwavettn.horoscope.databinding.PopupMenuLayoutBinding
import com.smartwavettn.horoscope.model.PersonalInformation

object CustomPopup {
    fun showPopupMenu(
        context: Context,
        listData: ArrayList<PersonalInformation>,
        anchor: View,
        openAddFiends: (Int,Boolean) -> Unit,
    ) {
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupViewBinding: PopupMenuLayoutBinding =
            PopupMenuLayoutBinding.inflate(inflater, null, false)

        val popupWindow = PopupWindow(
            popupViewBinding.root,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT, true
        )
        val adapter = PopupAdapter() {
            if (it == listData.size)
                openAddFiends.invoke(it, true)
            else{
                openAddFiends.invoke(it, false)
            }
            popupWindow.dismiss()
        }
        popupViewBinding.rcView.adapter = adapter
        adapter.submitList(listData)

        popupWindow.elevation = 5.0f
        val offset = 10.dpToPx(context.resources)
        popupWindow.showAsDropDown(anchor, 0, offset)
    }

}

