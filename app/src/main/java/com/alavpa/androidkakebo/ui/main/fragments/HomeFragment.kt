package com.alavpa.androidkakebo.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.adapters.CategoryAdapter
import com.alavpa.androidkakebo.base.BaseFragment
import com.alavpa.domain.entity.Category
import com.alavpa.presentation.home.HomePresenter
import com.alavpa.presentation.home.HomeView
import kotlinx.android.synthetic.main.activity_categories.categories
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<HomePresenter>(), HomeView {

    private val presenter: HomePresenter by inject()

    private val adapter = CategoryAdapter()

    override fun bindPresenter(): HomePresenter {
        return presenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categories?.layoutManager = LinearLayoutManager(activity)
        categories?.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.loadCategories()
    }

    override fun populateCategories(list: List<Category>) {
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
}