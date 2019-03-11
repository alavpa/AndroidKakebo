package com.alavpa.data

import com.alavpa.data.database.DatabaseSource
import com.alavpa.data.resources.ResourcesDataSource
import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Period
import com.alavpa.domain.entity.Transaction

class DataRepository(
    private val databaseSource: DatabaseSource,
    private val resourcesDataSource: ResourcesDataSource
) : Repository {
    override suspend fun getTransactions(): List<Transaction> {
        return databaseSource.getAllTransactions().map {
            it.toEntity(getCategory(it.categoryId), getPeriod(it.periodId))
        }
    }

    override suspend fun deleteCategory(category: Category): Int {
        return databaseSource.deleteCategory(category.toTable())
    }

    override suspend fun getCategory(id: Long): Category {
        return databaseSource.getCategory(id).toEntity()
    }

    override suspend fun getPeriod(id: Long?): Period? {
        return id?.let {
            databaseSource.getPeriod(it)?.toEntity()
        }
    }

    override suspend fun insertCategory(category: Category): Long {
        return databaseSource.insertCategory(category.toTable())
    }

    override suspend fun insertTransaction(transaction: Transaction): Long {
        val table = transaction.toTable()
        transaction.period?.let {
            table.periodId = databaseSource.insertPeriod(it.toTable())
        }
        return databaseSource.insertTransaction(table)
    }

    override suspend fun getCategories(): List<Category> {
        val tables = databaseSource.getAllCategories()
        return tables.map { it.toEntity() }
    }

    override suspend fun getIcons(): List<Int> {
        return resourcesDataSource.getIcons()
    }
}