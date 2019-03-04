package com.alavpa.domain

import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Transaction

interface Repository {
    suspend fun insertTransaction(transaction: Transaction): Long
    suspend fun getCategories(isIncome: Boolean): List<Category>
    suspend fun getCategory(id: Long): Category
    suspend fun insertCategory(category: Category): Long
}