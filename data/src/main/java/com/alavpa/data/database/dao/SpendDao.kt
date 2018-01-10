package com.alavpa.data.database.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.alavpa.data.database.entity.SpendTable
import com.alavpa.domain.entity.Spend
import io.reactivex.Single

/**
 * Created by alex on 10/11/2017.
 */
@Dao
interface SpendDao {

    @Query("SELECT * FROM SpendTable")
    fun getAllSpend(): Single<List<SpendTable>>

    @Insert(onConflict = REPLACE)
    fun insertSpend(spendTable : SpendTable) : Long

    @Update(onConflict = REPLACE)
    fun updateSpend(spendTable : SpendTable) : Int

    @Delete
    fun deleteSpend(spendTable: SpendTable) : Int
}