package com.alavpa.data.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by alex on 10/11/2017.
 */
@Entity
data class CategoryTable(
        @ColumnInfo @PrimaryKey(autoGenerate = true) var id : Long = 0,
        val name: String = "",
        val income: Boolean = false)
