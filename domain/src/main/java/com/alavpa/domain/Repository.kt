package com.alavpa.domain

import com.alavpa.domain.entity.Alarm
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Month
import com.alavpa.domain.entity.Spend

interface Repository {
    suspend fun insertSpend(spend: Spend): Long
    suspend fun insertAlarm(alarm: Alarm): Long
    suspend fun getCategories(isIncome: Boolean): List<Category>
    suspend fun getCategory(id: Long): Category
    suspend fun getCurrentMonth(): Month
    suspend fun insertCategory(category: Category): Long
}