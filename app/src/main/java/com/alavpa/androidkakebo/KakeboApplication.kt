package com.alavpa.androidkakebo

import android.app.Application
import com.alavpa.androidkakebo.di.modules
import org.koin.android.ext.android.startKoin
import timber.log.Timber

/**
 * Created by alex on 10/12/2017.
 */
class KakeboApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, modules)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}