package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Spend
import java.util.Date

class InsertSpend(private val repository: Repository) : UseCase<Long>() {

    var value: Float = 0f
    var isIncome: Boolean = false
    var categoryId: Long = -1

    override suspend fun execute(): Long {
        val category = repository.getCategory(categoryId)
        val spend = Spend(0, value, Date(), category, isIncome)
        return repository.insertSpend(spend)
    }
}