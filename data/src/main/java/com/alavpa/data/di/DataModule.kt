package com.alavpa.data.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.alavpa.data.DataRepository
import com.alavpa.data.database.DatabaseSource
import com.alavpa.data.database.KakeboDb
import com.alavpa.data.preferences.PreferencesDataSource
import com.alavpa.data.resources.ResourcesDataSource
import com.alavpa.domain.Repository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val dataModule = module {
    single { createDb(androidApplication()) }
    single { DatabaseSource(get()) }
    single { ResourcesDataSource(androidApplication()) }
    single { DataRepository(get(), get(), get()) } bind Repository::class
    single { PreferencesDataSource(getSharedPreferences(androidApplication())) }
}

fun getSharedPreferences(context: Context): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}

fun createDb(context: Context): KakeboDb {
    return Room
        .databaseBuilder(context, KakeboDb::class.java, KakeboDb.DB_NAME)
        .build()
}