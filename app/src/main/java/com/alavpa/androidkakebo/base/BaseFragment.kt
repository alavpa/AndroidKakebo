package com.alavpa.androidkakebo.base

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alavpa.androidkakebo.dialogs.LoadingDialog
import com.alavpa.androidkakebo.navigation.Navigation
import com.alavpa.presentation.base.BasePresenter
import com.alavpa.presentation.base.BaseView
import org.koin.android.ext.android.inject

abstract class BaseFragment<T : BasePresenter<*>> : Fragment(), BaseView {

    private var basePresenter: BasePresenter<BaseView>? = null
    private val loadingDialog: LoadingDialog by inject()
    val navigation: Navigation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNCHECKED_CAST")
        this.basePresenter = bindPresenter() as BasePresenter<BaseView>
    }

    override fun stopLoading() {
        loadingDialog.dismiss()
    }

    override fun showError(message: String?) {
        activity?.let {
            Toast.makeText(it, message, Toast.LENGTH_LONG).show()
        }
    }

    override fun showMessage(message: String?) {
        activity?.let {
            Toast.makeText(it, message, Toast.LENGTH_LONG).show()
        }
    }

    override fun startLoading(message: String) {
        activity?.let {
            loadingDialog.show(it, message)
        }
    }

    override fun onStart() {
        super.onStart()
        basePresenter?.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        basePresenter?.detachView()
    }

    abstract fun bindPresenter(): T
}
