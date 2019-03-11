package com.alavpa.domain.entity

import java.util.*

/**
 * Created by alex_avila on 3/11/17.
 */
class Transaction(val id: Long = 0,
                  val amount: Float,
                  val insertDate: Date,
                  val category: Category,
                  val period: Period? = null)