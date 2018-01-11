package com.alavpa.data.database.mapper

import com.alavpa.data.database.entity.CategoryTable
import com.alavpa.data.database.entity.SpendTable
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Spend
import java.util.*

/**
 * Created by alex on 10/01/2018.
 */

class DataMapper {

    fun entityToTable(spend: Spend): SpendTable
            = SpendTable(spend.id,
            spend.value,
            spend.income,
            spend.date.time,
            spend.category.id)


    fun tableToEntity(spendTable: SpendTable, category: Category): Spend
            = Spend(spendTable.id,
            spendTable.value,
            Date(spendTable.insertDate),
            category,
            spendTable.isIncome)


    fun tableToEntity(categoryTable: CategoryTable): Category
            = Category(categoryTable.id,
            categoryTable.name,
            categoryTable.income)


    fun entityToTable(category: Category): CategoryTable
            = CategoryTable(category.id,
            category.name,
            category.income)

}