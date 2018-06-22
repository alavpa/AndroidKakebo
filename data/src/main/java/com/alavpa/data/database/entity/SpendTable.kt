package com.alavpa.data.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by alex on 10/11/2017.
 */
@Entity
data class SpendTable(
        @ColumnInfo @PrimaryKey(autoGenerate = true) var id : Long = 0,
        var value : Float = 0f,
        var isIncome : Boolean = false,
        var insertDate : Long = 0,
        var categoryId : Long = 0)
