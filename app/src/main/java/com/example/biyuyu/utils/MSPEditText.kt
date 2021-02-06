package com.example.biyuyu.utils

import android.graphics.Typeface
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class MSPEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    init {
        applyFont()
    }

    private fun applyFont() {
        val typeface: Typeface =
            Typeface.createFromAsset(context.assets, "NunitoSans-Regular.ttf")
        setTypeface(typeface)
    }

}