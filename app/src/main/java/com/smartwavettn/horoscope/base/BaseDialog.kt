package com.smartwavettn.horoscope.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup

abstract class BaseDialog(context: Context) : Dialog(context) {

    private var onPositiveButtonClick: (() -> Unit)? = null
    private var onNegativeButtonClick: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window?.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
    }

    fun setPositiveButtonClickListener(listener: () -> Unit) {
        onPositiveButtonClick = listener
    }

    fun setNegativeButtonClickListener(listener: () -> Unit) {
        onNegativeButtonClick = listener
    }

    protected fun dismissDialog() {
        dismiss()
    }

    protected fun handlePositiveButtonClick() {
        onPositiveButtonClick?.invoke()
        dismissDialog()
    }

    protected open fun handleNegativeButtonClick() {
        onNegativeButtonClick?.invoke()
        dismissDialog()
    }
}