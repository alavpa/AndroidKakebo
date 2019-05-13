package com.alavpa.androidkakebo.ui.transactions

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.adapters.CategoryAdapter
import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.androidkakebo.dialogs.ConfirmationDialog
import com.alavpa.androidkakebo.dialogs.PeriodicityDialog
import com.alavpa.androidkakebo.navigation.Navigation
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Period
import com.alavpa.domain.entity.Transaction
import com.alavpa.presentation.transactions.TransactionPresenter
import com.alavpa.presentation.transactions.TransactionView
import kotlinx.android.synthetic.main.activity_transaction.add
import kotlinx.android.synthetic.main.activity_transaction.amount
import kotlinx.android.synthetic.main.activity_transaction.categories
import kotlinx.android.synthetic.main.activity_transaction.date
import kotlinx.android.synthetic.main.activity_transaction.every
import kotlinx.android.synthetic.main.activity_transaction.kakeboBar
import kotlinx.android.synthetic.main.activity_transaction.periodSwitch
import kotlinx.android.synthetic.main.activity_transaction.time
import kotlinx.android.synthetic.main.appbarlayout_kakebo.view.titleBar
import org.koin.android.ext.android.inject
import java.util.Calendar

class TransactionActivity : BaseActivity<TransactionPresenter>(), TransactionView, ConfirmationDialog.ConfirmationListener {

    private val presenter: TransactionPresenter by inject()
    private val adapter = CategoryAdapter()
    private var period: Period? = null
    private var internalCheck = false
    private var transactionId = 0L
    private val onConfirmationListener = object : PeriodicityDialog.ConfirmationListener {
        override fun onAccept(number: Int, period: Int) {
            presenter.setPeriod(number, period)
        }

        override fun onCancel() {
            period?.let {
                presenter.setPeriod(it.times, it.periodicity)
            } ?: {
                periodSwitch?.isChecked = false
            }.invoke()
        }
    }

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

        every?.isEnabled = periodSwitch.isChecked
        periodSwitch?.setOnCheckedChangeListener { _, isChecked ->

            every?.isEnabled = isChecked

            if (!internalCheck) {
                if (isChecked) {
                    PeriodicityDialog.newInstance().show(
                        this@TransactionActivity,
                        onConfirmationListener
                    )
                } else {
                    every?.text = getString(R.string.no_period)
                    this.period = null
                }
            }
        }

        every?.setOnClickListener {
            PeriodicityDialog.newInstance().show(
                this@TransactionActivity,
                onConfirmationListener
            )
        }

        date?.setOnClickListener {
            presenter.onDateClick()
        }

        time?.setOnClickListener {
            presenter.onTimeClick()
        }
    }

    override fun setPeriod(number: Int, period: Int) {
        every.text = getString(R.string.period, number, resources.getStringArray(R.array.period)[period])
        this.period = Period(this.period?.id ?: 0, times = number, periodicity = period)
    }

    override fun setCurrentDate(dateText: String?) {
        date?.text = dateText
    }

    override fun setCurrentTime(timeText: String?) {
        time?.text = timeText
    }

    override fun onResume() {
        super.onResume()
        val transactionId = intent?.getLongExtra(Navigation.EXTRA_TRANSACTION_ID, -1) ?: -1
        if (transactionId > 0) {
            presenter.loadTransaction(transactionId)
        }
        presenter.loadCategories()
        presenter.setCurrentDate(
            presenter.calendar.get(Calendar.YEAR),
            presenter.calendar.get(Calendar.MONTH),
            presenter.calendar.get(Calendar.DAY_OF_MONTH)
        )
        presenter.setCurrentTime(
            presenter.calendar.get(Calendar.HOUR_OF_DAY),
            presenter.calendar.get(Calendar.MINUTE)
        )
    }

    override fun populateCategories(list: List<Category>) {
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    override fun showSuccessMessage() {
        showMessage("Success")
    }

    override fun showAddIncome(amount: String) {
        val message = if (transactionId > 0) getString(R.string.new_edit_income, amount)
        else getString(R.string.new_income, amount)

        ConfirmationDialog.newInstance(message).show(this, this)
    }

    override fun showAddOutcome(amount: String) {
        val message = if (transactionId > 0) getString(R.string.new_edit_outcome, amount)
        else getString(R.string.new_outcome, amount)

        ConfirmationDialog.newInstance(message).show(this, this)
    }

    override fun onAccept() {
        if (transactionId > 0) {
            presenter.editTransaction(transactionId, amount.text.toString(), adapter.itemSelected, period)
        } else {
            presenter.insertTransaction(amount.text.toString(), adapter.itemSelected, period)
        }
    }

    override fun onCancel() {
        // no-op
    }

    override fun populateTransaction(transaction: Transaction) {
        kakeboBar.getToolbar().titleBar.text = getString(R.string.edit_transaction)
        add?.setImageResource(R.drawable.ic_round_save_24px)

        this.transactionId = transaction.id
        amount?.setText(transaction.amount.toString())
        adapter.itemSelected = transaction.category.id
        val calendar = Calendar.getInstance()
        calendar.time = transaction.insertDate
        presenter.setCurrentDate(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        presenter.setCurrentTime(
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE)
        )

        this.period = transaction.period
        transaction.period?.let {
            internalCheck = true
            periodSwitch?.isChecked = true
            internalCheck = false
            presenter.setPeriod(it.times, it.periodicity)
        }
    }

    override fun openTimeDialog(calendar: Calendar) {
        TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                presenter.setCurrentTime(hour, minute)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

    override fun openDateDialog(calendar: Calendar) {
        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                presenter.setCurrentDate(year, month, day)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}