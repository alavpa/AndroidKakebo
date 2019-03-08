package com.alavpa.androidkakebo.ui.categories

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.adapters.CategoryAdapter
import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.domain.entity.Category
import com.alavpa.presentation.categories.CategoryPresenter
import com.alavpa.presentation.categories.CategoryView
import kotlinx.android.synthetic.main.activity_categories.add
import kotlinx.android.synthetic.main.activity_categories.categories
import kotlinx.android.synthetic.main.activity_categories.kakeboBar
import org.koin.android.ext.android.inject

class CategoryActivity : BaseActivity<CategoryPresenter>(), CategoryView {

    private val presenter: CategoryPresenter by inject()

    private val adapter = CategoryAdapter(true,
        {
            presenter.onClickItem(it)
        },
        {
            presenter.onDeleteItem(it)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        setSupportActionBar(kakeboBar.getToolbar())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        categories?.layoutManager = LinearLayoutManager(this)
        categories?.adapter = adapter

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
        populateCategories(listOf())
    }

    override fun populateCategories(list: List<Category>) {
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    override fun openAddCategory() {
        navigation.openAddCategory(this)
    }
}