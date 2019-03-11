package com.alavpa.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey


@Entity
data class TransactionTable(
    @ColumnInfo @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var amount: Float = 0f,
    var insertDate: Long = 0,
    @ForeignKey(entity = CategoryTable::class, parentColumns = ["id"], childColumns = ["categoryId"], onDelete = CASCADE)
    var categoryId: Long = 0,
    @ForeignKey(entity = PeriodTable::class, parentColumns = ["id"], childColumns = ["periodId"])
    var periodId: Long? = null
)
