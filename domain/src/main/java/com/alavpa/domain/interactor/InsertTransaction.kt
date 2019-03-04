package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Transaction
import java.util.Date

class InsertTransaction(private val repository: Repository) : UseCase<Long>() {

    var value: Float = 0f
    var isIncome: Boolean = false
    var categoryId: Long = -1

    override suspend fun execute(): Long {
        val category = repository.getCategory(categoryId)
        val transaction = Transaction(value = value, insertDate = Date(), category = category)
        return repository.insertTransaction(transaction)
    }
}