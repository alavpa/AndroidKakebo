package com.alavpa.androidkakebo.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.androidkakebo.ui.main.fragments.HomeFragment
import com.alavpa.androidkakebo.ui.main.fragments.SettingsFragment
import com.alavpa.androidkakebo.ui.main.fragments.StatisticsFragment
import com.alavpa.presentation.main.MainPresenter
import com.alavpa.presentation.main.MainView
import kotlinx.android.synthetic.main.activity_main.bottom_bar
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), MainView {

    private val presenter by inject<MainPresenter>()

    private val homeFragment = HomeFragment()
    private val statisticsFragment = StatisticsFragment()
    private val settingsFragment = SettingsFragment()

    companion object {
        fun newIntent(ctx: Context): Intent {
            return Intent(ctx, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setBasePresenter(presenter)

        bottom_bar?.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.home -> supportFragmentManager.beginTransaction().replace(R.id.container, homeFragment, "home").commit()
                R.id.statistics -> supportFragmentManager.beginTransaction().replace(R.id.container, statisticsFragment, "statistics").commit()
                R.id.settings -> supportFragmentManager.beginTransaction().replace(R.id.container, settingsFragment, "settings").commit()
            }

            true
        }
        bottom_bar?.selectedItemId = R.id.home

    }
}
