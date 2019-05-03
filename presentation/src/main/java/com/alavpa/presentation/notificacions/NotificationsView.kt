package com.alavpa.presentation.notificacions

import com.alavpa.presentation.base.BaseView
import com.alavpa.presentation.model.NotificationItem

interface NotificationsView : BaseView {
    fun populateNotifications(list: List<NotificationItem>)
}