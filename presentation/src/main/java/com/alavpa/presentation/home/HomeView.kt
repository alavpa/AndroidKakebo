package com.alavpa.presentation.home

import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Transaction
import com.alavpa.presentation.base.BaseView

interface HomeView : BaseView {
    fun populateTransactions(list: List<Transaction>)
    fun showEmptyList()
    fun openAddTransaction()

}