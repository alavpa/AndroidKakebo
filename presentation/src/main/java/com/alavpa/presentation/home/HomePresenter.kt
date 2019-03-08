package com.alavpa.presentation.home

import com.alavpa.domain.interactor.GetTransactions
import com.alavpa.presentation.base.BasePresenter

class HomePresenter(private val getTransactions: GetTransactions) : BasePresenter<HomeView>() {

    fun onAddTransaction() {
        view?.openAddTransaction()
    }

    fun loadTransactions() {
        getTransactions.perform { view?.populateTransactions(it) }
    }
}
