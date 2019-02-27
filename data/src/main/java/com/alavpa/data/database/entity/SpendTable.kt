package com.alavpa.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class SpendTable(
    @ColumnInfo @PrimaryKey(autoGenerate = true) var id : Long = 0,
    var value : Float = 0f,
    var isIncome : Boolean = false,
    var insertDate : Long = 0,
    var categoryId : Long = 0)
