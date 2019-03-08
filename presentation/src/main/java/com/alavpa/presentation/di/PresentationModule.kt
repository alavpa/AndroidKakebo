package com.alavpa.presentation.di

import com.alavpa.presentation.categories.CategoryPresenter
import com.alavpa.presentation.categories.add.AddCategoryPresenter
import com.alavpa.presentation.home.HomePresenter
import com.alavpa.presentation.main.MainPresenter
import com.alavpa.presentation.settings.SettingsPresenter
import com.alavpa.presentation.transactions.TransactionPresenter
import org.koin.dsl.module.module

val presentationModule = module {
    factory { MainPresenter() }
    factory { SettingsPresenter() }
    factory { CategoryPresenter(get(), get()) }
    factory { HomePresenter() }
    factory { AddCategoryPresenter(get(), get()) }
    factory { TransactionPresenter(get(), get(), get()) }
}