package com.alavpa.androidkakebo.di.application

import android.content.Context
import com.alavpa.androidkakebo.base.KakeboApplication
import com.alavpa.androidkakebo.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by alex on 10/12/2017.
 */
@Module
class ApplicationModule(private val app: KakeboApplication) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = app

}