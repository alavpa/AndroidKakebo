package com.alavpa.androidkakebo.ui.categories.add

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.presentation.categories.add.AddCategoryPresenter
import com.alavpa.presentation.categories.add.AddCategoryView
import kotlinx.android.synthetic.main.activity_add_category.icons
import kotlinx.android.synthetic.main.activity_add_category.name
import kotlinx.android.synthetic.main.activity_add_category.save
import org.koin.android.ext.android.inject

class AddCategoryActivity : BaseActivity<AddCategoryPresenter>(), AddCategoryView {

    private val presenter: AddCategoryPresenter by inject()

    private val adapter = IconAdapter {
        presenter.onClickItem(it)
    }

    override fun bindPresenter(): AddCategoryPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        icons.layoutManager = GridLayoutManager(this, 5)
        icons.adapter = adapter

        save?.setOnClickListener {
            presenter.save(name.text.toString())
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
}