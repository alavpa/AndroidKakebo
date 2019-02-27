package com.alavpa.data

import com.alavpa.data.database.DatabaseSource
import com.alavpa.data.database.mapper.DataMapper
import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Alarm
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Month
import com.alavpa.domain.entity.Spend
import com.alavpa.domain.interactor.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DataRepository(private val databaseSource: DatabaseSource,
                     private val mapper: DataMapper) : Repository {

    override fun getCategory(id: Long, result: Result<Category>) {
        GlobalScope.launch {
            try {
                val table = databaseSource.getCategory(id)
                val entity = mapper.tableToEntity(table)
                result.onSuccess(entity)
            } catch (throwable: Throwable) {
                result.onError(throwable)
            }
        }
    }

    override fun insertCategory(category: Category, result: Result<Long>) {
        GlobalScope.launch {
            try {
                val res = databaseSource.insertCategory(mapper.entityToTable(category))
                result.onSuccess(res)
            } catch (throwable: Throwable) {
                result.onError(throwable)
            }
        }
    }

    override fun insertSpend(spend: Spend, result: Result<Long>) {
        GlobalScope.launch {
            try {
                val res = databaseSource.insertSpend(mapper.entityToTable(spend))
                result.onSuccess(res)
            } catch (throwable: Throwable) {
                result.onError(throwable)
            }
        }
    }

    override fun insertAlarm(alarm: Alarm): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCategories(isIncome: Boolean, result: Result<List<Category>>) {
        GlobalScope.launch {
            try {
                val tables = databaseSource.getAllCategories(isIncome)
                val list = tables.map { table -> mapper.tableToEntity(table) }
                result.onSuccess(list)
            } catch (throwable: Throwable) {
                result.onError(throwable)
            }
        }

    }

    override fun getCurrentMonth(): Month {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}