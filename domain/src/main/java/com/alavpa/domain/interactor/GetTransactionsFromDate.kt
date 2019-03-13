package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Transaction
import java.util.Calendar

class GetTransactionsFromDate(private val repository: Repository) : UseCase<List<Transaction>>() {

    var from: Long = 0

    override suspend fun execute(): List<Transaction> {
        return repository.getTransactionsFromDate(from)
    }
}