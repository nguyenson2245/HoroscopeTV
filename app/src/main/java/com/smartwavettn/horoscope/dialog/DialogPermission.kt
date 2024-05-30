package com.smartwavettn.horoscope.dialog

import android.content.Context
import android.widget.Button
import android.widget.TextView
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.BaseDialog

class DialogPermission(context: Context, private val title: String) : BaseDialog(context) {
    init {
        setContentView(R.layout.dialog_notification)

        val dialogTitle = findViewById<TextView>(R.id.dialogTitle)
        val positiveButton = findViewById<Button>(R.id.yesButton)
        val negativeButton = findViewById<Button>(R.id.noButton)

        dialogTitle.text = title

        positiveButton.setOnClickListener {
            handlePositiveButtonClick()
        }

        negativeButton.setOnClickListener {
            handleNegativeButtonClick()
        }
    }

}