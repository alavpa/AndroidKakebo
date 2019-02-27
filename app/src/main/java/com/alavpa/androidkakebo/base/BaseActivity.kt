package com.alavpa.androidkakebo.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alavpa.androidkakebo.loading.LoadingDialog
import com.alavpa.presentation.base.BasePresenter
import com.alavpa.presentation.base.BaseView

open class BaseActivity : AppCompatActivity(), BaseView {

    private lateinit var basePresenter: BasePresenter<BaseView>

    private val loadingDialog = LoadingDialog()
    override fun stopLoading() {
        loadingDialog.dismiss()
    }

    override fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun startLoading(message: String) {
        loadingDialog.show(this, message)
    }

    override fun onStart() {
        super.onStart()
        basePresenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        basePresenter.detachView()
    }

    fun setBasePresenter(basePresenter: BasePresenter<*>) {
        this.basePresenter = basePresenter as BasePresenter<BaseView>
    }
}

