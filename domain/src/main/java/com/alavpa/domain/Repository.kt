package com.alavpa.domain

import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Transaction

interface Repository {
    suspend fun insertTransaction(transaction: Transaction): Long
    suspend fun getCategories(): List<Category>
    suspend fun getCategory(id: Long): Category
    suspend fun insertCategory(category: Category): Long
    suspend fun getIcons(): List<Int>
    suspend fun deleteCategory(category: Category): Int
    suspend fun getTransactions(): List<Transaction>
}