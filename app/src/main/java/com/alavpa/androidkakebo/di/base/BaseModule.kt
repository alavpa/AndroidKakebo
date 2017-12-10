package com.alavpa.androidkakebo.di.base

import android.content.Context
import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.androidkakebo.di.qualifiers.ActivityContext
import com.alavpa.androidkakebo.di.qualifiers.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by alex on 10/12/2017.
 */
@Module
class BaseModule(private val activity: BaseActivity) {

    @ActivityScope
    @Provides
    @ActivityContext
    fun providesContext(): Context = activity
}