package com.alavpa.domain

import com.alavpa.domain.entity.Alarm
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Month
import com.alavpa.domain.entity.Spend
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by alex_avila on 3/11/17.
 */
interface Repository {

    fun insertSpend(spend: Spend) : Single<Long>
    fun insertAlarm(alarm: Alarm) : Single<Long>
    fun getCategories(isIncome : Boolean): Flowable<List<Category>>
    fun getCategory(id : Long): Single<Category>
    fun getCurrentMonth(): Single<Month>
    fun insertCategory(category: Category): Single<Long>

}