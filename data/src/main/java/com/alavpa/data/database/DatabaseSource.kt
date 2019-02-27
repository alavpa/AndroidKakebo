package com.alavpa.data.database

import com.alavpa.data.database.entity.CategoryTable
import com.alavpa.data.database.entity.SpendTable

/**
 * Created by alex on 09/01/2018.
 */

class DatabaseSource(private val db: KakeboDb) {

    suspend fun getAllSpend(): List<SpendTable> {
        //return db.spendDao().getAllSpend()
        TODO("Implement")
    }

    suspend fun insertSpend(spendTable: SpendTable): Long {
        return db.spendDao().insertSpend(spendTable)
    }

    suspend fun updateSpend(spendTable: SpendTable): Int {
        //return db.spendDao().updateSpend(spendTable)
        TODO("Implement")
    }

    suspend fun deleteSpend(spendTable: SpendTable): Int {
        //return db.spendDao().deleteSpend(spendTable)
        TODO("Implement")
    }

    suspend fun insertCategory(categoryTable: CategoryTable): Long {
        return db.categoryDao().insert(categoryTable)
    }

    suspend fun getAllCategories(isIncome: Boolean): List<CategoryTable> {
        return db.categoryDao().getAll(isIncome)
    }

    suspend fun getAllCategories(): List<CategoryTable> {
        return db.categoryDao().getAll()
    }

    suspend fun getCategory(id: Long): CategoryTable {
        return db.categoryDao().get(id)
    }
}