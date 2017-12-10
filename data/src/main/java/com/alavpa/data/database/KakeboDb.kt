package com.alavpa.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.alavpa.data.database.dao.SpendDao
import com.alavpa.data.database.entity.SpendTable

/**
 * Created by alex on 10/11/2017.
 */

@Database(entities = arrayOf(SpendTable::class), version = 1)
abstract class KakeboDb : RoomDatabase(){

    abstract fun spendDao() : SpendDao
}