package com.alavpa.androidkakebo.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import com.alavpa.androidkakebo.R
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.appbarlayout_kakebo.view.titleBar

class KakeboBar : AppBarLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.KakeboBar)
        titleBar?.text = array.getString(R.styleable.KakeboBar_titleBar)
        array.recycle()
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.appbarlayout_kakebo, this)
    }

    fun getToolbar(): Toolbar {
        return findViewById(R.id.toolbar)
    }
}