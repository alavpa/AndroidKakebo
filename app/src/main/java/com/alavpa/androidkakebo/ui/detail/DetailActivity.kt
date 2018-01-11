package com.alavpa.androidkakebo.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.presentation.detail.DetailPresenter
import com.alavpa.presentation.detail.DetailView
import org.koin.android.ext.android.inject

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

    private val presenter by inject<DetailPresenter>()

    private lateinit var rvCategories: RecyclerView
    private var adapter = CategoryAdapter({ position -> presenter.onItemClick(position) })
    private lateinit var btnCancel: Button
    private lateinit var btnDone: Button
    private lateinit var btnAdd: ImageView
    private lateinit var etCategory: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setBasePresenter(presenter)

        presenter.value = intent.getFloatExtra(EXTRA_VALUE, 0f)
        presenter.isIncome = intent.getBooleanExtra(EXTRA_IS_INCOME, false)

        rvCategories = findViewById(R.id.rv_categories)
        rvCategories.adapter = adapter
        rvCategories.layoutManager = LinearLayoutManager(this)

        btnCancel = findViewById(R.id.btn_cancel)
        btnCancel.setOnClickListener { finish() }

        btnDone = findViewById(R.id.btn_done)
        btnDone.setOnClickListener { presenter.done() }

        btnAdd = findViewById(R.id.btn_add)
        btnAdd.setOnClickListener({ presenter.add(etCategory.text.toString()) })

        etCategory = findViewById(R.id.et_category)

    }

    override fun onResume() {
        super.onResume()
        presenter.init()
    }

    override fun populateCategories(categories: List<String>, selectedCategory : Int) {
        adapter.items = categories
        adapter.itemSelected = selectedCategory

        adapter.notifyDataSetChanged()
    }
}