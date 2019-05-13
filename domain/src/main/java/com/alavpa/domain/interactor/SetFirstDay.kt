package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Notification.Companion.AUTO_ID
import com.alavpa.domain.entity.Notification.Companion.FIRST_DAY_ID
import com.alavpa.domain.entity.Notification.Companion.TARGET_ID

class SetFirstDay(private val repository: Repository) : UseCase<Unit>() {

    var timeInMillis = 0L
    override suspend fun execute() {
        repository.setFirstDay(timeInMillis)
    }
}