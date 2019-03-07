package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Category

class DeleteCategory(private val repository: Repository) : UseCase<Int>() {
    lateinit var category: Category

    override suspend fun execute(): Int {
        return repository.deleteCategory(category)
    }
}