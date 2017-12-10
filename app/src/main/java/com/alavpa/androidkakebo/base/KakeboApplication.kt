package com.alavpa.androidkakebo.base

import android.app.Application
import com.alavpa.androidkakebo.di.application.ApplicationComponent
import com.alavpa.androidkakebo.di.application.ApplicationModule
import com.alavpa.androidkakebo.di.application.DaggerApplicationComponent

/**
 * Created by alex on 10/12/2017.
 */
class KakeboApplication : Application() {

    val applicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent.inject(this)
    }
}