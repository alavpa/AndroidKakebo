package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Transaction

class DeleteTransaction(private val repository: Repository) : UseCase<Int>() {
    lateinit var transaction: Transaction

    override suspend fun execute(): Int {
        return repository.deleteTransaction(transaction)
    }
}