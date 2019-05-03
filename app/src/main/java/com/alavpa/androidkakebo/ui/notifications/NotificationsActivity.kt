package com.alavpa.androidkakebo.ui.notifications

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.adapters.NotificationsAdapter
import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.domain.entity.Notification
import com.alavpa.presentation.model.NotificationItem
import com.alavpa.presentation.notificacions.NotificationsPresenter
import com.alavpa.presentation.notificacions.NotificationsView
import kotlinx.android.synthetic.main.activity_notifications.kakeboBar
import kotlinx.android.synthetic.main.activity_notifications.notifications
import org.koin.android.ext.android.inject

class NotificationsActivity : BaseActivity<NotificationsPresenter>(), NotificationsView {

    private val presenter: NotificationsPresenter by inject()
    override fun bindPresenter(): NotificationsPresenter {
        return presenter
    }

    val adapter = NotificationsAdapter { id, isEnabled ->
        presenter.enableNotifications(id, isEnabled)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        setSupportActionBar(kakeboBar?.getToolbar())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        notifications?.layoutManager = LinearLayoutManager(this)
        notifications?.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.loadNotifications()
    }

    override fun populateNotifications(list: List<NotificationItem>) {
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
}