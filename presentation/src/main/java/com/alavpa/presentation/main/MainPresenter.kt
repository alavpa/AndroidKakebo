package com.alavpa.presentation.main

import com.alavpa.presentation.base.BasePresenter

/**
 * Created by alex_avila on 2/11/17.
 */
class MainPresenter : BasePresenter<MainView>() {

    private val model = MainViewModel(0f)

    fun onIncome(value: Float) {
        model.value = value
        view?.goToIncome(value)
    }

    fun onOutcome(value: Float) {
        model.value = value
        view?.goToOutcome(value)
    }

    fun init() {
        view?.render(model)
    }


}