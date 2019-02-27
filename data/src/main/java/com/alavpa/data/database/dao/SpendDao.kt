package com.alavpa.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.alavpa.data.database.entity.SpendTable

/**
 * Created by alex on 10/11/2017.
 */
@Dao
interface SpendDao {

    @Query("SELECT * FROM SpendTable")
    fun getAllSpend(): List<SpendTable>

    @Insert(onConflict = REPLACE)
    fun insertSpend(spendTable: SpendTable): Long

    @Delete
    fun deleteSpend(spendTable: SpendTable): Int
}