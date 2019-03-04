package com.alavpa.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.alavpa.data.database.entity.TransactionTable

/**
 * Created by alex on 10/11/2017.
 */
@Dao
interface TransactionDao {

    @Query("SELECT * FROM TransactionTable")
    suspend fun getAllTransactions(): List<TransactionTable>

    @Insert(onConflict = REPLACE)
    suspend fun insertSpend(transactionTable: TransactionTable): Long

    @Delete
    suspend fun deleteSpend(transactionTable: TransactionTable): Int
}