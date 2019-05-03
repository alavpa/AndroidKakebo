package com.alavpa.presentation.notificacions

import com.alavpa.domain.entity.Notification
import com.alavpa.presentation.base.BaseView

interface NotificationsView : BaseView {
    fun populateNotifications(list: List<Notification>)
}