package com.alavpa.domain.interactor

import com.alavpa.domain.entity.Spend
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

/**
 * Created by alex_avila on 3/11/17.
 */
class InsertSpend {
    lateinit var spend: Spend

    fun run(): Float {
        try {
            return 45.5f
        } catch (e: Throwable) {
            throw e
        }
    }
}