package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Transaction
import java.util.Date

class GetIcons(private val repository: Repository) : UseCase<List<Int>>() {

    override suspend fun execute(): List<Int> {
        return repository.getIcons()
    }
}