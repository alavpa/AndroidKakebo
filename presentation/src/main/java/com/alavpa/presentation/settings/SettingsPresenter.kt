package com.alavpa.presentation.settings

import com.alavpa.presentation.base.BasePresenter

class SettingsPresenter : BasePresenter<SettingsView>() {
    fun onClickCategories() {
        view?.openCategories()
    }

    fun onClickNotifications() {
        view?.openNotifications()
    }
}