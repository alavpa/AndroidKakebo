package com.alavpa.domain

import com.alavpa.domain.entity.Alarm
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Month
import com.alavpa.domain.entity.Spend

/**
 * Created by alex_avila on 3/11/17.
 */
interface Repository {

    fun insertSpend(spend: Spend)
    fun insertAlarm(alarm: Alarm)
    fun getCategories(isIncome : Boolean): List<Category>
    fun getCurrentMonth(): Month

}