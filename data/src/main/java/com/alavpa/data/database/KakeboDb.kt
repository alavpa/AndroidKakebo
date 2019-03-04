package com.alavpa.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alavpa.data.database.dao.CategoryDao
import com.alavpa.data.database.dao.TransactionDao
import com.alavpa.data.database.entity.CategoryTable
import com.alavpa.data.database.entity.TransactionTable

/**
 * Created by alex on 10/11/2017.
 */

@Database(
    entities = [TransactionTable::class, CategoryTable::class],
    exportSchema = false,
    version = KakeboDb.DB_VERSION
)
abstract class KakeboDb : RoomDatabase() {

    companion object {
        const val DB_NAME = "KakeboDB"
        const val DB_VERSION = 1
    }

    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
}