package com.alavpa.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.alavpa.data.database.dao.SpendDao
import com.alavpa.data.database.entity.CategoryTable
import com.alavpa.data.database.entity.SpendTable

/**
 * Created by alex on 10/11/2017.
 */

@Database(entities = arrayOf(SpendTable::class,CategoryTable::class),
        exportSchema = false,
        version = KakeboDb.DB_VERSION)
abstract class KakeboDb : RoomDatabase(){

    companion object {
        const val DB_NAME = "KakeboDB"
        const val DB_VERSION = 1
    }

    abstract fun spendDao() : SpendDao
}