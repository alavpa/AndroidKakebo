package com.alavpa.androidkakebo.ui.main

import android.os.Bundle
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.androidkakebo.ui.main.fragments.HomeFragment
import com.alavpa.androidkakebo.ui.main.fragments.SettingsFragment
import com.alavpa.androidkakebo.ui.main.fragments.StatisticsFragment
import com.alavpa.presentation.base.BasePresenter
import com.alavpa.presentation.base.BaseView
import com.alavpa.presentation.main.MainPresenter
import com.alavpa.presentation.main.MainView
import kotlinx.android.synthetic.main.activity_main.bottom_bar
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<MainPresenter>(), MainView {

    private val presenter by inject<MainPresenter>()

    private val homeFragment = HomeFragment()
    private val statisticsFragment = StatisticsFragment()
    private val settingsFragment = SettingsFragment()

    companion object {
        private const val TAG_HOME = "tag_home"
        private const val TAG_STATISTICS = "tag_statistics"
        private const val TAG_SETTINGS = "tag_settings"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_bar?.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.home -> presenter.onClickHome()
                R.id.statistics -> presenter.onClickStatistics()
                R.id.settings -> presenter.onClickSettings()
            }
            true
        }
        bottom_bar?.selectedItemId = R.id.home
    }

    override fun bindPresenter(): MainPresenter {
        return presenter
    }

    override fun openHome() {
        supportFragmentManager.beginTransaction().replace(R.id.container, homeFragment, TAG_HOME).commit()
    }

    override fun openStatistics() {
        supportFragmentManager.beginTransaction().replace(R.id.container, statisticsFragment, TAG_STATISTICS).commit()
    }

    override fun openSettings() {
        supportFragmentManager.beginTransaction().replace(R.id.container, settingsFragment, TAG_SETTINGS).commit()
    }
}
