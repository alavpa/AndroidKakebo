package com.alavpa.androidkakebo.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.alavpa.androidkakebo.R
import kotlinx.android.synthetic.main.switch_layout_kakebo.view.option1
import kotlinx.android.synthetic.main.switch_layout_kakebo.view.option2
import kotlinx.android.synthetic.main.switch_layout_kakebo.view.text1
import kotlinx.android.synthetic.main.switch_layout_kakebo.view.text2

class KakeboSwitch : LinearLayout {

    companion object {
        private const val OPTION_1 = 1
        private const val OPTION_2 = 2
    }

    var optionSelected = -1
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.KakeboSwitch)
        text1?.text = array.getString(R.styleable.KakeboSwitch_text1)
        text2?.text = array.getString(R.styleable.KakeboSwitch_text2)
        val selected = array.getInteger(R.styleable.KakeboSwitch_selected, -1)
        array.recycle()

        if (selected == OPTION_1) {
            selectOption1()
        }

        if (selected == OPTION_2) {
            selectOption2()
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.switch_layout_kakebo, this)
        setBackgroundResource(R.drawable.switch_background)
        option1?.setOnClickListener {
            selectOption1()
        }

        option2?.setOnClickListener {
            selectOption2()
        }
    }

    fun selectOption1(){
        optionSelected = 1
        option1?.isSelected = true
        option2?.isSelected = false
    }

    fun selectOption2(){
        optionSelected = 2
        option1?.isSelected = false
        option2?.isSelected = true
    }
}