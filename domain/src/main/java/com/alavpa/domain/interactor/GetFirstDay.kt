package com.alavpa.domain.interactor

import com.alavpa.domain.Repository

class GetFirstDay(private val repository: Repository) : UseCase<Long>() {

    override suspend fun execute(): Long {
        return repository.getFirstDay()
    }
}