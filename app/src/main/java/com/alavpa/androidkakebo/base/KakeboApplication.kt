package com.alavpa.androidkakebo.base

import android.app.Application
import com.alavpa.androidkakebo.di.modules
import org.koin.android.ext.android.startKoin

/**
 * Created by alex on 10/12/2017.
 */
class KakeboApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, modules)
    }
}