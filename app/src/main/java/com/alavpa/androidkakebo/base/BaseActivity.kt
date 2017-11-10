package com.alavpa.androidkakebo.base

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.alavpa.androidkakebo.loading.LoadingDialog
import com.alavpa.presentation.base.BaseView

open class BaseActivity : AppCompatActivity(), BaseView {

    private var loadingDialog: LoadingDialog? = null
    override fun stopLoading() {
        loadingDialog?.dismiss()
        loadingDialog = null
    }

    override fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun startLoading(message: String?) {

        if (loadingDialog == null) {
            loadingDialog = LoadingDialog()
        }

        loadingDialog?.show(message, this)


    }
}
