package com.alavpa.presentation.transactions

import com.alavpa.domain.interactor.GetCategories
import com.alavpa.domain.interactor.GetCategory
import com.alavpa.domain.interactor.InsertTransaction
import com.alavpa.presentation.base.BasePresenter

class TransactionPresenter(
    private val getCategories: GetCategories,
    private val getCategory: GetCategory,
    private val insertTransaction: InsertTransaction) : BasePresenter<TransactionView>() {

    fun loadCategories() {
        getCategories.perform { list ->
            view?.populateCategories(list)
        }
    }

    fun insertTransaction(amount: String, categoryId: Long) {
        insertTransaction.categoryId = categoryId
        insertTransaction.amount = amount.toFloatOrNull() ?: 0f

        insertTransaction.perform {
            if (it > 0) {
                view?.showSuccessMessage()
            }
        }
    }

    fun confirmTransaction(amount: String, itemSelected: Long) {
        getCategory.id = itemSelected
        getCategory.perform {
            if (it.income) {
                view?.showAddIncome(amount)
            } else {
                view?.showAddOutcome(amount)
            }
        }
    }
}