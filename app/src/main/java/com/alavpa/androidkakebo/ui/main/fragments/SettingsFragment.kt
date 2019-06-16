package com.alavpa.androidkakebo.ui.main.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.base.BaseFragment
import com.alavpa.presentation.settings.SettingsPresenter
import com.alavpa.presentation.settings.SettingsView
import kotlinx.android.synthetic.main.fragment_settings.categories
import kotlinx.android.synthetic.main.fragment_settings.first_day
import kotlinx.android.synthetic.main.fragment_settings.first_day_value
import kotlinx.android.synthetic.main.fragment_settings.notifications
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SettingsFragment : BaseFragment<SettingsPresenter>(), SettingsView {

    private val presenter: SettingsPresenter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        first_day?.setOnClickListener {
            presenter.onClickFirstDay()
        }

        categories?.setOnClickListener {
            presenter.onClickCategories()
        }

        notifications?.setOnClickListener {
            presenter.onClickNotifications()
        }

        presenter.init()
    }

    override fun bindPresenter(): SettingsPresenter {
        return presenter
    }

    override fun openCategories() {
        navigation.openCategories(activity)
    }

    override fun openNotifications() {
        navigation.openNotifications(activity)
    }

    override fun openDateDialog(timeInMillis: Long) {
        val currentCalendar = Calendar.getInstance()
        currentCalendar.timeInMillis = timeInMillis

        context?.let { ctx ->
            DatePickerDialog(
                ctx,
                DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    val cal = Calendar.getInstance()
                    cal.set(year, month, day)
                    presenter.setCurrentDate(cal.timeInMillis)
                },
                currentCalendar.get(Calendar.YEAR),
                currentCalendar.get(Calendar.MONTH),
                currentCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    override fun setFirstDay(timeInMillis: Long) {
        val cal = Calendar.getInstance()
        cal.timeInMillis = timeInMillis

        val firstDay = SimpleDateFormat("dd 'de' MMM", Locale.getDefault())
            .format(cal.time)

        first_day_value?.text = firstDay
    }
}