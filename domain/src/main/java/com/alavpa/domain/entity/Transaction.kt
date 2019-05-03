package com.alavpa.domain.entity

import java.util.Date

class Transaction(
    val id: Long = 0,
    val amount: Float,
    val insertDate: Date,
    val category: Category,
    val period: Period? = null
)