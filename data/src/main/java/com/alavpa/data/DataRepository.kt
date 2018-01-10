package com.alavpa.data

import com.alavpa.data.database.DatabaseSource
import com.alavpa.data.database.mapper.DataMapper
import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Alarm
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Month
import com.alavpa.domain.entity.Spend
import io.reactivex.Single

/**
 * Created by alex on 10/01/2018.
 */
class DataRepository(private val databaseSource: DatabaseSource,
                     private val mapper: DataMapper) : Repository {

    override fun insertCategory(category: Category): Single<Long> {
        return databaseSource.insertCategory(mapper.entityToTable(category))
    }

    override fun insertSpend(spend: Spend): Single<Long> {
        return databaseSource.insertSpend(mapper.entityToTable(spend))
    }

    override fun insertAlarm(alarm: Alarm): Single<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCategories(isIncome: Boolean): Single<List<Category>> {
        return databaseSource.getAllCategories()
                .map { tables -> tables.map { table -> mapper.tableToEntity(table) } }
    }

    override fun getCurrentMonth(): Single<Month> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}