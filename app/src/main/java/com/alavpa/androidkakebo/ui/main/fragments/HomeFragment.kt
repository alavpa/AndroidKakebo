package com.alavpa.androidkakebo.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.base.BaseFragment
import com.alavpa.presentation.home.HomePresenter
import kotlinx.android.synthetic.main.fragment_home.amount
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<HomePresenter>() {

    private val presenter: HomePresenter by inject()

    override fun bindPresenter(): HomePresenter {
        return presenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        amount?.setText("0.00")
    }
}