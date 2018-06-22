package com.alavpa.presentation.main

import com.alavpa.presentation.base.BasePresenter

/**
 * Created by alex_avila on 2/11/17.
 */
class MainPresenter : BasePresenter<MainView>() {

    private var value = 0f

    fun onIncome(value: Float) {
        this.value = value
        view?.goToIncome(value)
    }

    fun onOutcome(value: Float) {
        this.value = value
        view?.goToOutcome(value)
    }

    fun init() {
        view?.setValue(value)
    }

    fun clear() {
        value = 0f
    }


}