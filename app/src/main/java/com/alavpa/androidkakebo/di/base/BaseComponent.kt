package com.alavpa.androidkakebo.di.base

import android.content.Context
import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.androidkakebo.di.application.ApplicationComponent
import com.alavpa.androidkakebo.di.qualifiers.ActivityContext
import com.alavpa.androidkakebo.di.qualifiers.ActivityScope
import dagger.Component
import javax.inject.Singleton

/**
 * Created by alex on 10/12/2017.
 */
@ActivityScope
@Component(modules = [BaseModule::class], dependencies = [ApplicationComponent::class])
interface BaseComponent {
    fun inject(activity: BaseActivity)

    @ActivityContext
    fun context() : Context
}