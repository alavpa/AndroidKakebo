package com.alavpa.presentation.transactions

import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Transaction
import com.alavpa.presentation.base.BaseView

interface TransactionView : BaseView {
    fun populateCategories(list: List<Category>)
    fun showSuccessMessage()
    fun showAddIncome(amount: String)
    fun showAddOutcome(amount: String)
    fun setCurrentDate(dateText: String?)
    fun setCurrentTime(timeText: String?)
    fun setPeriod(number: Int, period: Int)
    fun populateTransaction(transaction: Transaction)
}