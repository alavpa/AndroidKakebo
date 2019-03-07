package com.alavpa.data

import com.alavpa.data.database.DatabaseSource
import com.alavpa.data.resources.ResourcesDataSource
import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Transaction

class DataRepository(
    private val databaseSource: DatabaseSource,
    private val resourcesDataSource: ResourcesDataSource
) : Repository {

    override suspend fun deleteCategory(category: Category): Int {
        return databaseSource.deleteCategory(category.toTable())
    }

    override suspend fun getCategory(id: Long): Category {
        return databaseSource.getCategory(id).toEntity()
    }

    override suspend fun insertCategory(category: Category): Long {
        return databaseSource.insertCategory(category.toTable())
    }

    override suspend fun insertTransaction(transaction: Transaction): Long {
        return databaseSource.insertTransaction(transaction.toTable())
    }

    override suspend fun getCategories(): List<Category> {
        val tables = databaseSource.getAllCategories()
        return tables.map { it.toEntity() }
    }

    override suspend fun getIcons(): List<Int> {
        return resourcesDataSource.getIcons()
    }
}