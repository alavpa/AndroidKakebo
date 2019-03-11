package com.alavpa.presentation.transactions

import com.alavpa.domain.entity.Period
import com.alavpa.domain.interactor.GetCategories
import com.alavpa.domain.interactor.GetCategory
import com.alavpa.domain.interactor.InsertTransaction
import com.alavpa.presentation.base.BasePresenter
import java.text.SimpleDateFormat
import java.util.Calendar

class TransactionPresenter(
    private val getCategories: GetCategories,
    private val getCategory: GetCategory,
    private val insertTransaction: InsertTransaction,
    private val dateFormat: SimpleDateFormat,
    private val timeFormat: SimpleDateFormat
) : BasePresenter<TransactionView>() {

    fun loadCategories() {
        getCategories.perform { list ->
            view?.populateCategories(list)
        }
    }

    fun insertTransaction(amount: String, categoryId: Long, period: Period?) {
        insertTransaction.categoryId = categoryId
        insertTransaction.amount = amount.toFloatOrNull() ?: 0f
        insertTransaction.period = period

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

    fun setCurrentDate() {

        val calendar = Calendar.getInstance()
        val date = dateFormat.format(calendar.time)
        view?.setCurrentDate(date)
    }

    fun setCurrentTime() {
        val calendar = Calendar.getInstance()
        val time = timeFormat.format(calendar.time)
        view?.setCurrentTime(time)
    }

    fun setPeriod(number: Int, period: Int) {
        view?.setPeriod(number, period)
    }
}