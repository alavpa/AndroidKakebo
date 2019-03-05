package com.alavpa.androidkakebo.ui.categories

import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.presentation.categories.CategoryPresenter
import org.koin.android.ext.android.inject

class CategoryActivity : BaseActivity<CategoryPresenter>() {
    private val presenter: CategoryPresenter by inject()
    override fun bindPresenter(): CategoryPresenter {
        return presenter
    }

}