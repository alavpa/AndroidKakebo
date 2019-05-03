package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Transaction

class GetTransaction(private val repository: Repository) : UseCase<Transaction>() {

    var transactionId = 0L
    override suspend fun execute(): Transaction {
        return repository.getTransaction(transactionId)
    }
}