package com.alavpa.androidkakebo.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.alavpa.androidkakebo.di.application.ApplicationComponent
import com.alavpa.androidkakebo.di.base.BaseComponent
import com.alavpa.androidkakebo.di.base.BaseModule
import com.alavpa.androidkakebo.di.base.DaggerBaseComponent
import com.alavpa.androidkakebo.loading.LoadingDialog
import com.alavpa.presentation.base.BaseView

open class BaseActivity : AppCompatActivity(), BaseView {

    val baseComponent : BaseComponent by lazy {
        DaggerBaseComponent.builder()
                .applicationComponent(getApplicationComponent())
                .baseModule(BaseModule(this))
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseComponent.inject(this)
    }

    private val loadingDialog = LoadingDialog()
    override fun stopLoading() {
        loadingDialog.dismiss()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun startLoading(message: String) {
        loadingDialog.show(message, this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return (application as KakeboApplication).applicationComponent
    }
}
