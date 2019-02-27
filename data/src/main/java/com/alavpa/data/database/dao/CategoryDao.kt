package com.alavpa.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.alavpa.data.database.entity.CategoryTable
import com.alavpa.data.database.entity.SpendTable

@Dao
interface CategoryDao {

    @Query("SELECT * FROM CategoryTable")
    suspend fun getAll(): List<CategoryTable>

    @Query("SELECT * FROM CategoryTable WHERE income = :income")
    suspend fun getAll(income : Boolean): List<CategoryTable>

    @Query("SELECT * FROM CategoryTable WHERE id = :id")
    suspend fun get(id: Long): CategoryTable

    @Insert(onConflict = REPLACE)
    suspend fun insert(table : CategoryTable) : Long

    @Delete
    suspend fun delete(table: SpendTable) : Int
}