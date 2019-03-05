package com.alavpa.presentation.main

import com.alavpa.presentation.base.BasePresenter

class MainPresenter : BasePresenter<MainView>() {
    fun onClickHome() {
        view?.openHome()
    }

    fun onClickStatistics() {
        view?.openStatistics()
    }

    fun onClickSettings() {
        view?.openSettings()
    }

}
