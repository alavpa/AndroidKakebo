package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Category

class GetCategories(private val repository: Repository) : UseCase<List<Category>>() {

    var isIncome = false

    override fun execute(result: Result<List<Category>>) {
        return repository.getCategories(isIncome, result)
    }
}