package com.alavpa.presentation.di

import com.alavpa.presentation.detail.DetailPresenter
import com.alavpa.presentation.main.MainPresenter
import org.koin.dsl.module.applicationContext

/**
 * Created by alex on 09/01/2018.
 */

val presentationModule = applicationContext{

    factory { MainPresenter() }
    factory { DetailPresenter(get(), get(), get()) }
}