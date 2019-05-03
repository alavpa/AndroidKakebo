package com.alavpa.data.database

import com.alavpa.data.database.entity.CategoryTable
import com.alavpa.data.database.entity.PeriodTable
import com.alavpa.data.database.entity.TransactionTable
import com.alavpa.domain.entity.Transaction

class DatabaseSource(private val db: KakeboDb) {

    suspend fun getAllTransactions(): List<TransactionTable> {
        return db.transactionDao().getAllTransactions()
    }

    suspend fun insertTransaction(transactionTable: TransactionTable): Long {
        return db.transactionDao().insertTransaction(transactionTable)
    }

    suspend fun updateTransaction(transactionTable: TransactionTable): Int {
        //return db.spendDao().updateSpend(transactionTable)
        TODO("Implement")
    }

    suspend fun deleteTransaction(transactionTable: TransactionTable): Int {
        return db.transactionDao().deleteTransaction(transactionTable)
    }

    suspend fun deleteCategory(categoryTable: CategoryTable): Int {
        return db.categoryDao().delete(categoryTable)
    }

    suspend fun insertCategory(categoryTable: CategoryTable): Long {
        return db.categoryDao().insert(categoryTable)
    }

    suspend fun getAllCategories(): List<CategoryTable> {
        return db.categoryDao().getAll()
    }

    suspend fun getCategory(id: Long): CategoryTable {
        return db.categoryDao().get(id)
    }

    suspend fun getPeriod(id: Long): PeriodTable? {
        return db.periodDao().get(id)
    }

    suspend fun insertPeriod(period: PeriodTable): Long {
        return db.periodDao().insert(period)
    }

    suspend fun getTransaction(id: Long): TransactionTable {
        return db.transactionDao().getTransaction(id)
    }

    suspend fun getTransactionsByDate(from: Long): List<TransactionTable> {
        return db.transactionDao().getTransactionsFromDate(from)
    }
}