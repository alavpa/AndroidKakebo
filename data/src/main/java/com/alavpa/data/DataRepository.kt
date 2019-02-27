package com.alavpa.data

import com.alavpa.data.database.DatabaseSource
import com.alavpa.data.database.mapper.DataMapper
import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Alarm
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Month
import com.alavpa.domain.entity.Spend
import kotlinx.coroutines.delay

class DataRepository(private val databaseSource: DatabaseSource,
                     private val mapper: DataMapper) : Repository {

    override suspend fun getCategory(id: Long): Category {
        val table = databaseSource.getCategory(id)
        return mapper.tableToEntity(table)
    }

    override suspend fun insertCategory(category: Category): Long {
        return databaseSource.insertCategory(mapper.entityToTable(category))
    }

    override suspend fun insertSpend(spend: Spend): Long {
        return databaseSource.insertSpend(mapper.entityToTable(spend))
    }

    override suspend fun insertAlarm(alarm: Alarm): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getCategories(isIncome: Boolean): List<Category> {
        val tables = databaseSource.getAllCategories(isIncome)
        delay(10000)
        return tables.map { table -> mapper.tableToEntity(table) }
    }

    override suspend fun getCurrentMonth(): Month {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}