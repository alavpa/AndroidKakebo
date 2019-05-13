package com.alavpa.presentation.settings

import com.alavpa.presentation.base.BaseView

interface SettingsView : BaseView {
    fun openCategories()
    fun openNotifications()
    fun openDateDialog(timeInMillis: Long)
    fun setFirstDay(timeInMillis: Long)
}