package com.alavpa.androidkakebo.ui.main

import android.os.Bundle
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.androidkakebo.ui.main.fragments.HomeFragment
import com.alavpa.androidkakebo.ui.main.fragments.SettingsFragment
import com.alavpa.androidkakebo.ui.main.fragments.StatisticsFragment
import com.alavpa.presentation.main.MainPresenter
import com.alavpa.presentation.main.MainView
import kotlinx.android.synthetic.main.activity_main.bottom_bar
import kotlinx.android.synthetic.main.activity_main.kakeboBar
import kotlinx.android.synthetic.main.appbarlayout_kakebo.view.titleBar
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : BaseActivity<MainPresenter>(), MainView {

    private val presenter by inject<MainPresenter>()

    private val homeFragment = HomeFragment()
    private val statisticsFragment = StatisticsFragment()
    private val settingsFragment = SettingsFragment()

    private var selectedFragment = -1

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
    }

    override fun bindPresenter(): MainPresenter {
        return presenter
    }

    override fun onResume() {
        super.onResume()
        if (selectedFragment == -1) {
            bottom_bar?.selectedItemId = R.id.home
        }
    }

    override fun openHome() {
        selectedFragment = R.id.home
        kakeboBar?.titleBar?.setText(R.string.home)
        supportFragmentManager.beginTransaction().replace(R.id.container, homeFragment, TAG_HOME).commit()
    }

    override fun openStatistics() {
        selectedFragment = R.id.statistics
        kakeboBar?.titleBar?.setText(R.string.statistics)
        supportFragmentManager.beginTransaction().replace(R.id.container, statisticsFragment, TAG_STATISTICS).commit()
    }

    override fun openSettings() {
        selectedFragment = R.id.settings
        kakeboBar?.titleBar?.setText(R.string.settings)
        supportFragmentManager.beginTransaction().replace(R.id.container, settingsFragment, TAG_SETTINGS).commit()
    }
}
