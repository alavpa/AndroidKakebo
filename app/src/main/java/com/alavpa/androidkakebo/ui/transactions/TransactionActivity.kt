package com.alavpa.androidkakebo.ui.transactions

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.CalendarView
import androidx.recyclerview.widget.LinearLayoutManager
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.adapters.CategoryAdapter
import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.androidkakebo.dialogs.ConfirmationDialog
import com.alavpa.androidkakebo.dialogs.PeriodicityDialog
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Period
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
import org.koin.android.ext.android.inject
import java.util.Calendar

class TransactionActivity : BaseActivity<TransactionPresenter>(), TransactionView, ConfirmationDialog.ConfirmationListener {

    private val presenter: TransactionPresenter by inject()
    private val adapter = CategoryAdapter()
    private var period: Period? = null

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

            if (isChecked) {
                PeriodicityDialog.newInstance().show(
                    this@TransactionActivity,
                    object : PeriodicityDialog.ConfirmationListener {
                        override fun onAccept(number: Int, period: Int) {
                            presenter.setPeriod(number, period)
                        }

                        override fun onCancel() {
                            // no-op
                        }
                    }
                )
            } else {
                every?.text = getString(R.string.no_period)
                this.period = null
            }

        }

        every?.setOnClickListener {
            PeriodicityDialog.newInstance().show(
                this@TransactionActivity,
                object : PeriodicityDialog.ConfirmationListener {
                    override fun onAccept(number: Int, period: Int) {
                        presenter.setPeriod(number, period)
                    }

                    override fun onCancel() {
                        // no-op
                    }
                }
            )
        }

        date?.setOnClickListener {
            DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    presenter.setCurrentDate(year, month, day)
                },
                presenter.calendar.get(Calendar.YEAR),
                presenter.calendar.get(Calendar.MONTH),
                presenter.calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        time?.setOnClickListener {
            TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                    presenter.setCurrentTime(hour, minute)
                },
                presenter.calendar.get(Calendar.HOUR_OF_DAY),
                presenter.calendar.get(Calendar.MINUTE),
                true
            ).show()
        }
    }

    override fun setPeriod(number: Int, period: Int) {
        every.text = getString(R.string.period, number, resources.getStringArray(R.array.period)[period])
        this.period = Period(times = number, periodicity = period)
    }

    override fun setCurrentDate(dateText: String?) {
        date?.text = dateText
    }

    override fun setCurrentTime(timeText: String?) {
        time?.text = timeText
    }

    override fun onResume() {
        super.onResume()
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
        val message = getString(R.string.new_income, amount)
        ConfirmationDialog.newInstance(message).show(this, this)
    }

    override fun showAddOutcome(amount: String) {
        val message = getString(R.string.new_outcome, amount)
        ConfirmationDialog.newInstance(message).show(this, this)
    }

    override fun onAccept() {
        presenter.insertTransaction(amount.text.toString(), adapter.itemSelected, period)
    }

    override fun onCancel() {
        // no-op
    }


}