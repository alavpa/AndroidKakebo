package com.alavpa.domain

import com.alavpa.domain.entity.Alarm
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Month
import com.alavpa.domain.entity.Spend

interface Repository {
    fun insertSpend(spend: Spend) : Long
    fun insertAlarm(alarm: Alarm) : Long
    fun getCategories(isIncome : Boolean): List<Category>
    fun getCategory(id : Long): Category
    fun getCurrentMonth(): Month
    fun insertCategory(category: Category): Long
}