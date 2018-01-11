package com.alavpa.domain.di

import com.alavpa.domain.Repository
import com.alavpa.domain.interactor.GetCategories
import com.alavpa.domain.interactor.GetCategory
import com.alavpa.domain.interactor.InsertCategory
import com.alavpa.domain.interactor.InsertSpend
import org.koin.dsl.module.applicationContext

/**
 * Created by alex on 09/01/2018.
 */
val domainModule = applicationContext{

    bean { GetCategory(get()) }
    bean { GetCategories(get()) }
    bean { InsertSpend (get())}
    bean { InsertCategory (get())}

}