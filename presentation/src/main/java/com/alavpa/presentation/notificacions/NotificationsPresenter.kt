package com.alavpa.presentation.notificacions

import com.alavpa.domain.interactor.EnableNotifications
import com.alavpa.domain.interactor.GetNotificationsList
import com.alavpa.presentation.base.BasePresenter
import com.alavpa.presentation.model.NotificationItem

class NotificationsPresenter(
    private val getNotificationsList: GetNotificationsList,
    private val enableNotifications: EnableNotifications
) : BasePresenter<NotificationsView>() {

    fun loadNotifications() {
        getNotificationsList.perform { list ->
            view?.populateNotifications(list.map { NotificationItem(it.id, it.text, it.selected) })
        }
    }

    fun enableNotifications(id: Long, enabled: Boolean) {
        enableNotifications.id = id
        enableNotifications.isEnabled = enabled
        enableNotifications.perform()
    }
}