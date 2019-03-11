package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Period
import com.alavpa.domain.entity.Transaction
import java.util.Calendar
import java.util.Date

class InsertTransaction(private val repository: Repository) : UseCase<Long>() {

    var date: Date = Calendar.getInstance().time
    var amount: Float = 0f
    var categoryId: Long = -1
    var period: Period? = null

    override suspend fun execute(): Long {
        val category = repository.getCategory(categoryId)
        val transaction = Transaction(amount = amount, insertDate = date, category = category, period = period)
        return repository.insertTransaction(transaction)
    }
}