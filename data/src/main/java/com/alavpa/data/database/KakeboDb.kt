package com.alavpa.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alavpa.data.database.dao.CategoryDao
import com.alavpa.data.database.dao.SpendDao
import com.alavpa.data.database.entity.CategoryTable
import com.alavpa.data.database.entity.SpendTable

/**
 * Created by alex on 10/11/2017.
 */

@Database(
    entities = [SpendTable::class, CategoryTable::class],
    exportSchema = false,
    version = KakeboDb.DB_VERSION
)
abstract class KakeboDb : RoomDatabase() {

    companion object {
        const val DB_NAME = "KakeboDB"
        const val DB_VERSION = 1
    }

    abstract fun spendDao(): SpendDao
    abstract fun categoryDao(): CategoryDao
}