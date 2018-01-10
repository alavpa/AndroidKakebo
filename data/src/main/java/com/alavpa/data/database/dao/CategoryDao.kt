package com.alavpa.data.database.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.alavpa.data.database.entity.CategoryTable
import com.alavpa.data.database.entity.SpendTable
import com.alavpa.domain.entity.Spend
import io.reactivex.Single

/**
 * Created by alex on 10/11/2017.
 */
@Dao
interface CategoryDao {

    @Query("SELECT * FROM CategoryTable")
    fun getAll(): Single<List<CategoryTable>>

    @Query("SELECT * FROM CategoryTable WHERE income = :income")
    fun getAll(income : Boolean): Single<List<CategoryTable>>

    @Insert(onConflict = REPLACE)
    fun insert(table : CategoryTable) : Long

    @Update(onConflict = REPLACE)
    fun update(table : CategoryTable) : Int

    @Delete
    fun delete(table: SpendTable) : Int
}