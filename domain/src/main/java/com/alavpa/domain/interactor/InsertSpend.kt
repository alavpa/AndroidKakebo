package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Spend
import java.util.Date

class InsertSpend(private val repository: Repository) : UseCase<Long>() {
    var value: Float = 0f
    var isIncome: Boolean = false
    var categoryId: Long = -1

    override fun execute(result: Result<Long>) {
        repository.getCategory(categoryId, onResultCategory(result))
    }

    private fun onResultCategory(result: Result<Long>) = Result<Category>(
        {
            val spend = Spend(0, value, Date(), it, isIncome)
            repository.insertSpend(spend, result)
        },
        {
            result.onError(it)
        }
    )
}