package com.alavpa.presentation.di

import com.alavpa.presentation.detail.DetailPresenter
import com.alavpa.presentation.main.MainPresenter
import com.alavpa.presentation.mapper.ViewMapper
import org.koin.dsl.module.module

val presentationModule = module {

    single { ViewMapper() }
    factory { MainPresenter() }
    factory { DetailPresenter(get(), get(), get(), get()) }
}