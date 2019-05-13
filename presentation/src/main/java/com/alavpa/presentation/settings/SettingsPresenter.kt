package com.alavpa.presentation.settings

import com.alavpa.domain.interactor.GetFirstDay
import com.alavpa.domain.interactor.SetFirstDay
import com.alavpa.presentation.base.BasePresenter

class SettingsPresenter(
    private val getFirstDay: GetFirstDay,
    private val setFirstDay: SetFirstDay
) : BasePresenter<SettingsView>() {

    fun onClickCategories() {
        view?.openCategories()
    }

    fun onClickNotifications() {
        view?.openNotifications()
    }

    fun setCurrentDate(timeInMillis: Long) {
        setFirstDay.timeInMillis = timeInMillis
        setFirstDay.perform {
            view?.setFirstDay(timeInMillis)
        }
    }

    fun onClickFirstDay() {
        getFirstDay.perform {
            view?.openDateDialog(it)
        }
    }

    fun init() {
        getFirstDay.perform {
            view?.setFirstDay(it)
        }
    }
}