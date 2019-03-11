package com.alavpa.androidkakebo.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.adapters.TransactionAdapter
import com.alavpa.androidkakebo.base.BaseFragment
import com.alavpa.domain.entity.Transaction
import com.alavpa.presentation.home.HomePresenter
import com.alavpa.presentation.home.HomeView
import kotlinx.android.synthetic.main.fragment_home.add
import kotlinx.android.synthetic.main.fragment_home.transactions
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<HomePresenter>(), HomeView {

    private val presenter: HomePresenter by inject()

    private val adapter = TransactionAdapter(onItemClickCallback = {
        navigation.openEditTransaction(this@HomeFragment.activity, it)
    })

    override fun bindPresenter(): HomePresenter {
        return presenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transactions?.layoutManager = LinearLayoutManager(activity)
        transactions?.adapter = adapter

        add?.setOnClickListener {
            presenter.onAddTransaction()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.loadTransactions()
    }

    override fun populateTransactions(list: List<Transaction>) {
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    override fun showEmptyList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openAddTransaction() {
        navigation.openTransaction(activity)
    }

}