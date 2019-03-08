package com.alavpa.presentation.home

import com.alavpa.presentation.base.BasePresenter

class HomePresenter : BasePresenter<HomeView>() {

    fun onAddTransaction() {
        view?.openAddTransaction()
    }

    fun loadTransactions() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
