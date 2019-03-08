package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Transaction
import java.util.Date

class GetTransactions(private val repository: Repository) : UseCase<List<Transaction>>() {

    override suspend fun execute(): List<Transaction> {
        return repository.getTransactions()
    }
}