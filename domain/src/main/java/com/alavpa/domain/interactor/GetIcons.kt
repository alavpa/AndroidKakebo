package com.alavpa.domain.interactor

import com.alavpa.domain.Repository

class GetIcons(private val repository: Repository) : UseCase<List<Int>>() {

    override suspend fun execute(): List<Int> {
        return repository.getIcons()
    }
}