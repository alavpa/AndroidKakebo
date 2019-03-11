package com.alavpa.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey


@Entity
data class PeriodTable(
    @ColumnInfo @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var times: Int = 0,
    var periodicity: Int = 0
)
