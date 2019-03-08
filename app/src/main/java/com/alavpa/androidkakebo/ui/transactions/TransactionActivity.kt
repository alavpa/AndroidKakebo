package com.alavpa.androidkakebo.ui.transactions

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.adapters.CategoryAdapter
import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.androidkakebo.dialogs.ConfirmationDialog
import com.alavpa.domain.entity.Category
import com.alavpa.presentation.transactions.TransactionPresenter
import com.alavpa.presentation.transactions.TransactionView
import kotlinx.android.synthetic.main.activity_transaction.add
import kotlinx.android.synthetic.main.activity_transaction.amount
import kotlinx.android.synthetic.main.activity_transaction.categories
import kotlinx.android.synthetic.main.activity_transaction.kakeboBar
import org.koin.android.ext.android.inject

class TransactionActivity : BaseActivity<TransactionPresenter>(), TransactionView, ConfirmationDialog.ConfirmationListener {

    private val presenter: TransactionPresenter by inject()
    private val adapter = CategoryAdapter()

    override fun bindPresenter(): TransactionPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        setSupportActionBar(kakeboBar.getToolbar())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        categories?.layoutManager = LinearLayoutManager(this)
        categories?.adapter = adapter

        add?.setOnClickListener {
            presenter.confirmTransaction(amount.text.toString(), adapter.itemSelected)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.loadCategories()
    }

    override fun populateCategories(list: List<Category>) {
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    override fun showSuccessMessage() {
        showMessage("Success")
    }

    override fun showAddIncome(amount: String) {
        val message = getString(R.string.new_income, amount)
        ConfirmationDialog.newInstance(message).show(this, this)
    }

    override fun showAddOutcome(amount: String) {
        val message = getString(R.string.new_outcome, amount)
        ConfirmationDialog.newInstance(message).show(this, this)
    }

    override fun onAccept() {
        presenter.insertTransaction(amount.text.toString(), adapter.itemSelected)
    }

    override fun onCancel() {
        // no-op
    }


}