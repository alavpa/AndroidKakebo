package com.alavpa.data.database

import android.arch.persistence.room.Room
import android.content.Context
import com.alavpa.data.database.entity.SpendTable
import io.reactivex.Single
import java.util.concurrent.Callable

/**
 * Created by alex on 09/01/2018.
 */

class DatabaseSource(private val db : KakeboDb) {

//    private val db =  Room
//                .databaseBuilder(context, KakeboDb::class.java, "KakeboDb")
//                .build()


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
}