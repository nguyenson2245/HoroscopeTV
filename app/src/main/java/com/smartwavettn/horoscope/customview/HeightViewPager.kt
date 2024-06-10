package com.smartwavettn.horoscope.customview

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager

class HeightViewPager (context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasureSpecMod = heightMeasureSpec
        var height = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
            val h = child.measuredHeight
            if (h > height) height = h
        }
        if (height != 0) {
            heightMeasureSpecMod = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpecMod)
    }
    fun updateHeight(position: Int) {
        val child = getChildAt(position)
        child?.let {
            child.measure(
                MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            )
            val height = child.measuredHeight
            val params = layoutParams
            params.height = height
            layoutParams = params
        }

    }
}