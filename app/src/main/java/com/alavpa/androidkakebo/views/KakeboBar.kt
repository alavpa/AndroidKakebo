package com.alavpa.androidkakebo.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import com.alavpa.androidkakebo.R
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.appbarlayout_kakebo.view.toolbar

class KakeboBar : AppBarLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        LayoutInflater.from(context).inflate(R.layout.appbarlayout_kakebo, this)
    }

    fun getToolbar(): Toolbar {
        return findViewById(R.id.toolbar)
    }
}