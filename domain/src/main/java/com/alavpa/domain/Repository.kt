package com.alavpa.domain

import com.alavpa.domain.entity.Alarm
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Month
import com.alavpa.domain.entity.Spend
import com.alavpa.domain.interactor.Result

interface Repository {
    fun insertSpend(spend: Spend, result: Result<Long>)
    fun insertAlarm(alarm: Alarm): Long
    fun getCategories(isIncome: Boolean, result: Result<List<Category>>)
    fun getCategory(id: Long, result: Result<Category>)
    fun getCurrentMonth(): Month
    fun insertCategory(category: Category, result: Result<Long>)
}