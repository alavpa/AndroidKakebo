package com.alavpa.androidkakebo.di.application

import android.content.Context
import com.alavpa.androidkakebo.base.KakeboApplication
import com.alavpa.androidkakebo.di.qualifiers.ApplicationContext
import dagger.Component
import javax.inject.Singleton

/**
 * Created by alex on 10/12/2017.
 */

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(app: KakeboApplication)

    @ApplicationContext
    fun context() : Context
}