package com.alavpa.domain.di

import com.alavpa.domain.interactor.DeleteCategory
import com.alavpa.domain.interactor.DeleteTransaction
import com.alavpa.domain.interactor.EnableNotifications
import com.alavpa.domain.interactor.GetCategories
import com.alavpa.domain.interactor.GetCategory
import com.alavpa.domain.interactor.GetIcons
import com.alavpa.domain.interactor.GetIncomeFromDate
import com.alavpa.domain.interactor.GetNotificationsList
import com.alavpa.domain.interactor.GetOutcomeFromDate
import com.alavpa.domain.interactor.GetTransaction
import com.alavpa.domain.interactor.GetTransactions
import com.alavpa.domain.interactor.GetTransactionsFromDate
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
    factory { DeleteTransaction(get()) }
    factory { GetIncomeFromDate(get()) }
    factory { GetOutcomeFromDate(get()) }
    factory { GetTransactionsFromDate(get()) }
    factory { GetNotificationsList(get()) }
    factory { EnableNotifications(get()) }
}