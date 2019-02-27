package com.alavpa.data.database

import com.alavpa.data.database.entity.CategoryTable
import com.alavpa.data.database.entity.SpendTable

/**
 * Created by alex on 09/01/2018.
 */

class DatabaseSource(private val db: KakeboDb) {

    fun getAllSpend(): List<SpendTable> {
        //return db.spendDao().getAllSpend()
        TODO("Implement")
    }

    fun insertSpend(spendTable: SpendTable): Long {
        return db.spendDao().insertSpend(spendTable)
    }

    fun updateSpend(spendTable: SpendTable): Int {
        //return db.spendDao().updateSpend(spendTable)
        TODO("Implement")
    }

    fun deleteSpend(spendTable: SpendTable): Int {
        //return db.spendDao().deleteSpend(spendTable)
        TODO("Implement")
    }

    fun insertCategory(categoryTable: CategoryTable): Long {
        return db.categoryDao().insert(categoryTable)
    }

    fun getAllCategories(isIncome: Boolean): List<CategoryTable> {
        return db.categoryDao().getAll(isIncome)
    }

    fun getAllCategories(): List<CategoryTable> {
        return db.categoryDao().getAll()
    }

    fun getCategory(id: Long): CategoryTable {
        return db.categoryDao().get(id)
    }
}