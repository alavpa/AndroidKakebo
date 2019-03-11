package com.alavpa.androidkakebo.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.alavpa.androidkakebo.R
import kotlinx.android.synthetic.main.switch_layout_kakebo.view.icon1
import kotlinx.android.synthetic.main.switch_layout_kakebo.view.icon2
import kotlinx.android.synthetic.main.switch_layout_kakebo.view.option1
import kotlinx.android.synthetic.main.switch_layout_kakebo.view.option2
import kotlinx.android.synthetic.main.switch_layout_kakebo.view.text1
import kotlinx.android.synthetic.main.switch_layout_kakebo.view.text2
import timber.log.Timber

class KakeboSwitch : LinearLayout {

    companion object {
        const val OPTION_1 = 1
        const val OPTION_2 = 2
    }

    var optionSelected = -1
    private var onSwitchChangedListener: OnSwitchChangedListener? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.KakeboSwitch)
        text1?.text = array.getString(R.styleable.KakeboSwitch_text1)
        text2?.text = array.getString(R.styleable.KakeboSwitch_text2)
        val selected = array.getInteger(R.styleable.KakeboSwitch_selected, -1)
        val icon1ResId = array.getResourceId(R.styleable.KakeboSwitch_icon1, -1)
        val icon2ResId = array.getResourceId(R.styleable.KakeboSwitch_icon2, -1)
        val textSize = array.getDimension(R.styleable.KakeboSwitch_android_textSize, context.resources.getDimension(R.dimen.font_normal))
        array.recycle()

        if (selected == OPTION_1) {
            selectOption1()
        }

        if (selected == OPTION_2) {
            selectOption2()
        }

        if (icon1ResId > 0) {
            icon1?.setImageResource(icon1ResId)
        } else {
            icon1?.visibility = View.GONE
        }

        if (icon2ResId > 0) {
            icon2?.setImageResource(icon2ResId)
        } else {
            icon2?.visibility = View.GONE
        }

        val normal = context.resources.getDimension(R.dimen.font_normal)
        Timber.d("normal: $normal")
        val big = context.resources.getDimension(R.dimen.font_big)
        Timber.d("big: $big")
        text1?.textSize = textSize
        text2?.textSize = textSize
        Timber.d("textsize: $textSize")
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.switch_layout_kakebo, this)
        setBackgroundResource(R.drawable.switch_background)
        option1?.setOnClickListener {
            if (optionSelected != OPTION_1) {
                selectOption1()
                onSwitchChangedListener?.onSwitchChanged(OPTION_1)
            }
        }

        option2?.setOnClickListener {
            if (optionSelected != OPTION_2) {
                selectOption2()
                onSwitchChangedListener?.onSwitchChanged(OPTION_2)
            }
        }
    }

    fun selectOption1() {
        optionSelected = 1
        option1?.isSelected = true
        option2?.isSelected = false
    }

    fun selectOption2() {
        optionSelected = 2
        option1?.isSelected = false
        option2?.isSelected = true
    }

    fun setOnSwitchChanged(onSwitchChangedListener: OnSwitchChangedListener) {
        this.onSwitchChangedListener = onSwitchChangedListener

    }

    interface OnSwitchChangedListener {
        fun onSwitchChanged(option: Int)
    }
}