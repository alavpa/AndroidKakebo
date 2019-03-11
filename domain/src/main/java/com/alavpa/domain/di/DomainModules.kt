package com.alavpa.domain.di

import com.alavpa.domain.interactor.DeleteCategory
import com.alavpa.domain.interactor.GetCategories
import com.alavpa.domain.interactor.GetCategory
import com.alavpa.domain.interactor.GetIcons
import com.alavpa.domain.interactor.GetTransaction
import com.alavpa.domain.interactor.GetTransactions
import com.alavpa.domain.interactor.InsertCategory
import com.alavpa.domain.interactor.InsertTransaction
import org.koin.dsl.module.module

val domainModule = module {
    factory { GetCategory(get()) }
    factory { GetCategories(get()) }
    factory { InsertTransaction(get()) }
    factory { InsertCategory(get()) }
    factory { GetIcons(get()) }
    factory { DeleteCategory(get()) }
    factory { GetTransactions(get()) }
    factory { GetTransaction(get()) }
}