package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Notification

class GetNotificationsList(private val repository: Repository) : UseCase<List<Notification>>() {
    override suspend fun execute(): List<Notification> {
        return repository.getNotificationsList()
    }
}