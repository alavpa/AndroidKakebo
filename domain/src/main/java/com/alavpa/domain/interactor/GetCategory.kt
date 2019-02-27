package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Category

class GetCategory(private val repository: Repository) : UseCase<Category>() {

    var id = -1L

    override suspend fun execute(): Category {
        return repository.getCategory(id)
    }
}