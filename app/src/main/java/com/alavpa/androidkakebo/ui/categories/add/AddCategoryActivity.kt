package com.alavpa.androidkakebo.ui.categories.add

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.adapters.IconAdapter
import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.presentation.categories.add.AddCategoryPresenter
import com.alavpa.presentation.categories.add.AddCategoryView
import kotlinx.android.synthetic.main.activity_add_category.icons
import kotlinx.android.synthetic.main.activity_add_category.name
import kotlinx.android.synthetic.main.activity_add_category.save
import kotlinx.android.synthetic.main.activity_add_category.type
import kotlinx.android.synthetic.main.activity_add_category.kakeboBar
import org.koin.android.ext.android.inject

class AddCategoryActivity : BaseActivity<AddCategoryPresenter>(), AddCategoryView {

    private val presenter: AddCategoryPresenter by inject()

    private val adapter = IconAdapter()

    override fun bindPresenter(): AddCategoryPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        setSupportActionBar(kakeboBar.getToolbar())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        icons?.layoutManager = GridLayoutManager(this, 5)
        icons?.adapter = adapter

        save?.setOnClickListener {
            presenter.save(adapter.itemSelected, name?.text.toString(), type?.optionSelected)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.loadItems()
    }

    override fun populateIcons(icons: List<Int>) {
        adapter.items = icons
        adapter.notifyDataSetChanged()
    }

    override fun categorySaved() {
        finish()
    }
}