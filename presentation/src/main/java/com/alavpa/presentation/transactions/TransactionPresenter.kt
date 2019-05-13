package com.alavpa.presentation.transactions

import com.alavpa.domain.entity.Period
import com.alavpa.domain.interactor.GetCategories
import com.alavpa.domain.interactor.GetCategory
import com.alavpa.domain.interactor.GetTransaction
import com.alavpa.domain.interactor.InsertTransaction
import com.alavpa.presentation.base.BasePresenter
import java.text.SimpleDateFormat
import java.util.Calendar

class TransactionPresenter(
    private val getTransaction: GetTransaction,
    private val getCategories: GetCategories,
    private val getCategory: GetCategory,
    private val insertTransaction: InsertTransaction,
    private val dateFormat: SimpleDateFormat,
    private val timeFormat: SimpleDateFormat
) : BasePresenter<TransactionView>() {

    val calendar: Calendar = Calendar.getInstance()

    fun loadCategories() {
        getCategories.perform { list ->
            view?.populateCategories(list)
        }
    }

    fun insertTransaction(amount: String, categoryId: Long, period: Period?) {
        insertTransaction.categoryId = categoryId
        insertTransaction.amount = amount.toFloatOrNull() ?: 0f
        insertTransaction.period = period
        insertTransaction.date = calendar.time

        insertTransaction.perform {
            if (it > 0) {
                view?.showSuccessMessage()
            }
        }
    }

    fun editTransaction(transactionId: Long, amount: String, categoryId: Long, period: Period?) {
        insertTransaction.transactionId = transactionId
        insertTransaction.categoryId = categoryId
        insertTransaction.amount = amount.toFloatOrNull() ?: 0f
        insertTransaction.period = period
        insertTransaction.date = calendar.time

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

    fun setCurrentDate(year: Int, month: Int, day: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        view?.setCurrentDate(dateFormat.format(calendar.time))
    }

    fun setCurrentTime(hour: Int, minute: Int) {
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        view?.setCurrentTime(timeFormat.format(calendar.time))
    }

    fun setPeriod(number: Int, period: Int) {
        view?.setPeriod(number, period)
    }

    fun loadTransaction(transactionId: Long) {
        getTransaction.transactionId = transactionId
        getTransaction.perform {
            view?.populateTransaction(it)
        }
    }

    fun onDateClick() {
        view?.openDateDialog(calendar)
    }

    fun onTimeClick() {
        view?.openTimeDialog(calendar)
    }
}