package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Spend
import io.reactivex.Single

/**
 * Created by alex_avila on 3/11/17.
 */
class InsertSpend(private val repository: Repository) {
    lateinit var spend: Spend

    fun execute(): Single<Long> {
        return repository.insertSpend(spend)
    }
}