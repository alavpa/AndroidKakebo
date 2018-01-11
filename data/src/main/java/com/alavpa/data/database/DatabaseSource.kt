package com.alavpa.data.database

import com.alavpa.data.database.entity.CategoryTable
import com.alavpa.data.database.entity.SpendTable
import io.reactivex.Single

/**
 * Created by alex on 09/01/2018.
 */

class DatabaseSource(private val db: KakeboDb) {

    fun getAllSpend(): Single<List<SpendTable>> {
        //return db.spendDao().getAllSpend()
        TODO("Implement")
    }

    fun insertSpend(spendTable: SpendTable): Single<Long> {
        return Single.fromCallable({ db.spendDao().insertSpend(spendTable) })
    }

    fun updateSpend(spendTable: SpendTable): Single<Int> {
        //return db.spendDao().updateSpend(spendTable)
        TODO("Implement")
    }

    fun deleteSpend(spendTable: SpendTable): Single<Int> {
        //return db.spendDao().deleteSpend(spendTable)
        TODO("Implement")
    }

    fun insertCategory(categoryTable: CategoryTable) : Single<Long>{
        return Single.fromCallable({db.categoryDao().insert(categoryTable)})
    }

    fun getAllCategories(isIncome : Boolean): Single<List<CategoryTable>> {
        return db.categoryDao().getAll(isIncome)
    }

    fun getAllCategories(): Single<List<CategoryTable>> {
        return db.categoryDao().getAll()
    }
}