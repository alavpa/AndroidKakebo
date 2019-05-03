package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Notification.Companion.AUTO_ID
import com.alavpa.domain.entity.Notification.Companion.FIRST_DAY_ID
import com.alavpa.domain.entity.Notification.Companion.TARGET_ID

class EnableNotifications(private val repository: Repository) : UseCase<Unit>() {

    var id: Long = 0L
    var isEnabled = false
    override suspend fun execute() {
        when (id) {
            FIRST_DAY_ID -> repository.enableFirstDayNotifications(isEnabled)
            AUTO_ID -> repository.enableAutoNotifications(isEnabled)
            TARGET_ID -> repository.enableTargetNotifications(isEnabled)
        }
    }
}