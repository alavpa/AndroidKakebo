package com.alavpa.data.di

import android.app.Application
import com.alavpa.data.database.DatabaseSource
import com.alavpa.data.database.KakeboDb
import org.koin.dsl.module.applicationContext
import android.arch.persistence.room.Room
import android.content.Context
import com.alavpa.data.DataRepository
import com.alavpa.data.database.mapper.DataMapper
import com.alavpa.domain.Repository
import org.koin.Koin
import org.koin.KoinContext
import org.koin.android.ext.koin.androidApplication


/**
 * Created by alex on 09/01/2018.
 */

val dataModule = applicationContext{

    bean { DataMapper() }
    bean { createDb(androidApplication())}
    bean { DatabaseSource(get())}
    provide {DataRepository(get(),get())} bind Repository::class

}

fun createDb(context: Context) : KakeboDb{
    return Room
            .databaseBuilder(context, KakeboDb::class.java, "KakeboDb")
            .build()
}