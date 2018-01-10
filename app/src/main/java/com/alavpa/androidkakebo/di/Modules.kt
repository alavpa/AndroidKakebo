package com.alavpa.androidkakebo.di

import com.alavpa.data.di.dataModule
import com.alavpa.domain.di.domainModule
import com.alavpa.presentation.di.presentationModule

/**
 * Created by alex on 08/01/2018.
 */

val modules = listOf(appModule, presentationModule, domainModule, dataModule)