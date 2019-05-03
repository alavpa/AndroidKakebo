package com.alavpa.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.alavpa.data.database.entity.TransactionTable

@Dao
interface TransactionDao {

    @Query("SELECT * FROM TransactionTable ORDER BY insertDate ASC")
    suspend fun getAllTransactions(): List<TransactionTable>

    @Query("SELECT * FROM TransactionTable WHERE id=:id")
    suspend fun getTransaction(id: Long): TransactionTable

    @Insert(onConflict = REPLACE)
    suspend fun insertTransaction(transactionTable: TransactionTable): Long

    @Delete
    suspend fun deleteTransaction(transactionTable: TransactionTable): Int

    @Query("SELECT * FROM TransactionTable WHERE insertDate>:from ORDER BY insertDate ASC")
    suspend fun getTransactionsFromDate(from: Long): List<TransactionTable>
}