package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Category

class InsertCategory(private val repository: Repository) : UseCase<Long>() {
    lateinit var category: Category

    override suspend fun execute(): Long {
        return repository.insertCategory(category)
    }
}