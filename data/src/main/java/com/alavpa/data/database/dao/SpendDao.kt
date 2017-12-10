package com.alavpa.data.database.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.alavpa.data.database.entity.SpendTable
import com.alavpa.domain.entity.Spend

/**
 * Created by alex on 10/11/2017.
 */
@Dao
interface SpendDao {

    @Query("SELECT * FROM SpendTable")
    fun getAllSpend(): List<SpendTable>

    @Insert(onConflict = REPLACE)
    fun insertSpend(spendTable : SpendTable)

    @Update(onConflict = REPLACE)
    fun updateSpend(spendTable : SpendTable)

    @Delete
    fun deleteSpend(spendTable: SpendTable)
}