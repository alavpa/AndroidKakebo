package com.alavpa.data.di

import android.content.Context
import androidx.room.Room
import com.alavpa.data.DataRepository
import com.alavpa.data.database.DatabaseSource
import com.alavpa.data.database.KakeboDb
import com.alavpa.data.database.mapper.DataMapper
import com.alavpa.domain.Repository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module


/**
 * Created by alex on 09/01/2018.
 */

val dataModule = module {

    single { DataMapper() }
    single { createDb(androidApplication()) }
    single { DatabaseSource(get()) }
    single { DataRepository(get(), get()) } bind Repository::class
}

fun createDb(context: Context): KakeboDb {
    return Room
        .databaseBuilder(context, KakeboDb::class.java, KakeboDb.DB_NAME)
        .build()
}