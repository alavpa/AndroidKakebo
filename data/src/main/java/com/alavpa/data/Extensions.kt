package com.alavpa.data

import com.alavpa.data.database.entity.CategoryTable
import com.alavpa.data.database.entity.TransactionTable
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Transaction
import java.util.Calendar

fun TransactionTable.toEntity(): Transaction {
    val cal = Calendar.getInstance()
    cal.timeInMillis = this.insertDate
    return Transaction(this.id, this.value, cal.time, null)
}

fun Transaction.toTable(): TransactionTable {
    val cal = Calendar.getInstance()
    cal.time = this.insertDate
    return TransactionTable(this.id, this.value, cal.timeInMillis, this.category?.id ?: 0)
}

fun CategoryTable.toEntity(): Category {
    return Category(this.id, this.icon, this.name, this.income)
}

fun Category.toTable(): CategoryTable {
    return CategoryTable(this.id, this.icon, this.name, this.income)
}