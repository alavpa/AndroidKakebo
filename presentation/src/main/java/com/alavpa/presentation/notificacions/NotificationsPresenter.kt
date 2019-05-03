package com.alavpa.presentation.notificacions

import com.alavpa.domain.interactor.GetNotificationsList
import com.alavpa.presentation.base.BasePresenter

class NotificationsPresenter(
    private val getNotificationsList: GetNotificationsList
) : BasePresenter<NotificationsView>() {

    fun loadNotifications() {
        getNotificationsList.perform {
            view?.populateNotifications(it)
        }
    }
}