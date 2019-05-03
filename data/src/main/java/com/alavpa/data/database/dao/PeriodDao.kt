package com.alavpa.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.alavpa.data.database.entity.PeriodTable

@Dao
interface PeriodDao {

    @Query("SELECT * FROM PeriodTable")
    suspend fun getAll(): List<PeriodTable>

    @Query("SELECT * FROM PeriodTable WHERE id = :id")
    suspend fun get(id: Long): PeriodTable

    @Insert(onConflict = REPLACE)
    suspend fun insert(table: PeriodTable): Long

    @Delete
    suspend fun delete(table: PeriodTable): Int
}