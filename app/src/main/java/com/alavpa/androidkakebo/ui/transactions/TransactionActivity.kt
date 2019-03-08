package com.alavpa.androidkakebo.ui.transactions

import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.presentation.transactions.TransactionPresenter
import org.koin.android.ext.android.inject

class TransactionActivity : BaseActivity<TransactionPresenter>() {

    private val presenter: TransactionPresenter by inject()
    override fun bindPresenter(): TransactionPresenter {
        return presenter
    }
}