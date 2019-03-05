package com.alavpa.androidkakebo.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.base.BaseFragment
import com.alavpa.presentation.settings.SettingsPresenter
import com.alavpa.presentation.settings.SettingsView
import kotlinx.android.synthetic.main.fragment_settings.categories
import org.koin.android.ext.android.inject

class SettingsFragment : BaseFragment<SettingsPresenter>(), SettingsView {

    private val presenter: SettingsPresenter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categories?.setOnClickListener {
            presenter.onClickCategories()
        }

    }

    override fun bindPresenter(): SettingsPresenter {
        return presenter
    }

    override fun openCategories() {
        navigation.openCategories(activity)
    }
}