package com.alavpa.androidkakebo.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.presentation.detail.DetailPresenter
import com.alavpa.presentation.detail.DetailView
import com.alavpa.presentation.detail.DetailViewModel

/**
 * Created by alex_avila on 8/11/17.
 */
class DetailActivity : BaseActivity(), DetailView {

    companion object {
        private val EXTRA_VALUE = "EXTRA_VALUE"
        private val EXTRA_IS_INCOME = "EXTRA_IS_INCOME"
        fun newIntent(context: Context, value: Float, isIncome: Boolean): Intent
                = Intent(context, DetailActivity::class.java)
                .putExtra(EXTRA_VALUE, value)
                .putExtra(EXTRA_IS_INCOME, isIncome)
    }

    private val presenter = DetailPresenter(this)
    private lateinit var rvCategories: RecyclerView
    private var adapter = CategoryAdapter({ position -> presenter.onItemClick(position) })
    private lateinit var btnCancel: Button
    private lateinit var btnDone: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        presenter.value = intent.getFloatExtra(EXTRA_VALUE, 0f)
        presenter.isIncome = intent.getBooleanExtra(EXTRA_IS_INCOME, false)

        rvCategories = findViewById(R.id.rv_categories)
        rvCategories.adapter = adapter
        rvCategories.layoutManager = LinearLayoutManager(this)

        btnCancel = findViewById(R.id.btn_cancel)
        btnCancel.setOnClickListener { finish() }

        btnDone = findViewById(R.id.btn_done)
        btnDone.setOnClickListener { presenter.done() }

    }

    override fun onResume() {
        super.onResume()
        presenter.init()
    }

    override fun render(model: DetailViewModel) {
        adapter.items = model.categories
        adapter.itemSelected = model.selectedCategory

        adapter.notifyDataSetChanged()
    }
}