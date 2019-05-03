package com.alavpa.androidkakebo.ui.notifications

import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.presentation.notificacions.NotificationsPresenter
import com.alavpa.presentation.notificacions.NotificationsView
import org.koin.android.ext.android.inject

class NotificationsActivity : BaseActivity<NotificationsPresenter>(), NotificationsView {
    private val presenter: NotificationsPresenter by inject()
    override fun bindPresenter(): NotificationsPresenter {
        return presenter
    }
}