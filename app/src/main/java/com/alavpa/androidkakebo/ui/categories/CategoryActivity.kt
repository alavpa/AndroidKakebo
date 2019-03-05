package com.alavpa.androidkakebo.ui.categories

import android.os.Bundle
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.domain.entity.Category
import com.alavpa.presentation.categories.CategoryPresenter
import com.alavpa.presentation.categories.CategoryView
import kotlinx.android.synthetic.main.activity_categories.add
import kotlinx.android.synthetic.main.activity_categories.kakeboBar
import org.koin.android.ext.android.inject

class CategoryActivity : BaseActivity<CategoryPresenter>(), CategoryView {

    private val presenter: CategoryPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        setSupportActionBar(kakeboBar.getToolbar())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        add?.setOnClickListener {
            presenter.onClickAdd()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getCategories()
    }

    override fun bindPresenter(): CategoryPresenter {
        return presenter
    }

    override fun showEmptyList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun populateCategories(list: List<Category>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openAddCategory() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}