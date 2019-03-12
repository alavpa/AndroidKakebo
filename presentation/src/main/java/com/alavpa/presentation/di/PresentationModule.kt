package com.alavpa.presentation.di

import com.alavpa.presentation.categories.CategoryPresenter
import com.alavpa.presentation.categories.add.AddCategoryPresenter
import com.alavpa.presentation.home.HomePresenter
import com.alavpa.presentation.main.MainPresenter
import com.alavpa.presentation.settings.SettingsPresenter
import com.alavpa.presentation.transactions.TransactionPresenter
import org.koin.dsl.module.module
import java.text.SimpleDateFormat
import java.util.Locale

val presentationModule = module {
    factory { MainPresenter() }
    factory { SettingsPresenter() }
    factory { CategoryPresenter(get(), get()) }
    factory { HomePresenter(get(), get()) }
    factory { AddCategoryPresenter(get(), get()) }
    factory("date") { getSimpleDateFormat() }
    factory("time") { getSimpleTimeFormat() }
    factory { TransactionPresenter(get(), get(), get(), get(), get("date"), get("time")) }
}

fun getSimpleDateFormat(): SimpleDateFormat {
    return SimpleDateFormat("dd 'de' MMMM 'de' YYYY", Locale("es", "ES"))
}

fun getSimpleTimeFormat(): SimpleDateFormat {
    return SimpleDateFormat("'a las' HH:mm", Locale.GERMAN)
}