package com.alavpa.androidkakebo.ui.categories

import android.os.Bundle
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.presentation.categories.CategoryPresenter
import kotlinx.android.synthetic.main.activity_categories.kakeboBar
import org.koin.android.ext.android.inject

class CategoryActivity : BaseActivity<CategoryPresenter>() {
    private val presenter: CategoryPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        setSupportActionBar(kakeboBar.getToolbar())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun bindPresenter(): CategoryPresenter {
        return presenter
    }

}