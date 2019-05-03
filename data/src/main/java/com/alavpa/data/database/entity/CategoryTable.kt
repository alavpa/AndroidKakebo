package com.alavpa.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryTable(
    @ColumnInfo @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var icon: Int = 0,
    var name: String = "",
    var income: Boolean = false
)
