package com.alavpa.presentation.main

import com.alavpa.presentation.base.BaseView

/**
 * Created by alex_avila on 2/11/17.
 */
interface MainView : BaseView{

    fun goToIncome(value : Float)
    fun goToOutcome(value : Float)
    fun setValue(value: Float)
}