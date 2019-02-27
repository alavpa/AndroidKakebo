package com.alavpa.data

import com.alavpa.data.database.DatabaseSource
import com.alavpa.data.database.mapper.DataMapper
import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Alarm
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Month
import com.alavpa.domain.entity.Spend

class DataRepository(private val databaseSource: DatabaseSource,
                     private val mapper: DataMapper) : Repository {

    override fun getCategory(id: Long): Category {
        val table = databaseSource.getCategory(id)
        return mapper.tableToEntity(table)
    }

    override fun insertCategory(category: Category): Long {
        return databaseSource.insertCategory(mapper.entityToTable(category))
    }

    override fun insertSpend(spend: Spend): Long {
        return databaseSource.insertSpend(mapper.entityToTable(spend))
    }

    override fun insertAlarm(alarm: Alarm): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCategories(isIncome: Boolean): List<Category> {
        val tables = databaseSource.getAllCategories(isIncome)
        return tables.map { table -> mapper.tableToEntity(table) }
    }

    override fun getCurrentMonth(): Month {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}