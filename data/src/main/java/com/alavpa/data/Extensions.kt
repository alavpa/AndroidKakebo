package com.alavpa.data

import com.alavpa.data.database.entity.CategoryTable
import com.alavpa.data.database.entity.TransactionTable
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Transaction
import java.util.Calendar

fun TransactionTable.toEntity(category: Category): Transaction {
    val cal = Calendar.getInstance()
    cal.timeInMillis = this.insertDate
    return Transaction(this.id, this.amount, cal.time, category)
}

fun Transaction.toTable(): TransactionTable {
    val cal = Calendar.getInstance()
    cal.time = this.insertDate
    return TransactionTable(this.id, this.amount, cal.timeInMillis, this.category.id)
}

fun CategoryTable.toEntity(): Category {
    return Category(this.id, this.icon, this.name, this.income)
}

fun Category.toTable(): CategoryTable {
    return CategoryTable(this.id, this.icon, this.name, this.income)
}