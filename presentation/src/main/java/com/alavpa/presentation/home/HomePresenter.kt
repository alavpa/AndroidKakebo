package com.alavpa.presentation.home

import com.alavpa.domain.entity.Transaction
import com.alavpa.domain.interactor.DeleteTransaction
import com.alavpa.domain.interactor.GetTransactions
import com.alavpa.presentation.base.BasePresenter

class HomePresenter(
    private val getTransactions: GetTransactions,
    private val deleteTransaction: DeleteTransaction
) : BasePresenter<HomeView>() {

    fun onAddTransaction() {
        view?.openAddTransaction()
    }

    fun loadTransactions() {
        getTransactions.perform { view?.populateTransactions(it) }
    }

    fun onRemoveTransaction(transaction: Transaction) {
        deleteTransaction.transaction = transaction
        deleteTransaction.perform {
            if (it > 0) {
                loadTransactions()
            }
        }
    }
}
