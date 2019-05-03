package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Transaction

class GetOutcomeFromDate(private val repository: Repository) : UseCase<List<Transaction>>() {

    var from: Long = 0

    override suspend fun execute(): List<Transaction> {
        return repository.getTransactionsFromDate(from).filter {
            !it.category.income
        }
    }
}