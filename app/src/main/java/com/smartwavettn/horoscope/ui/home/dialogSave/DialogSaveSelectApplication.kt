package com.smartwavettn.horoscope.ui.home.dialogSave

import android.content.Context
import android.widget.Button
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.BaseDialog

class DialogSaveSelectApplication(context: Context, private val title: String) :
    BaseDialog(context) {
    init {
        setContentView(R.layout.dialog_save_select)

        val positiveButton = findViewById<Button>(R.id.yesButton)
        val negativeButton = findViewById<Button>(R.id.noButton)

        positiveButton.setOnClickListener {
            handlePositiveButtonClick()
        }

        negativeButton.setOnClickListener {
            handleNegativeButtonClick()
        }
    }

}