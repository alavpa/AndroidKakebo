package com.alavpa.androidkakebo.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.alavpa.androidkakebo.Navigation
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.base.BaseActivity
import com.alavpa.presentation.base.BasePresenter
import com.alavpa.presentation.base.BaseView
import com.alavpa.presentation.main.MainPresenter
import com.alavpa.presentation.main.MainView
import com.alavpa.presentation.main.MainViewModel
import org.koin.android.ext.android.inject
import java.text.DecimalFormat

class MainActivity : BaseActivity(), MainView {

    private val df by inject<DecimalFormat>()
    private val navigation by inject<Navigation>()

    private lateinit var etValue: EditText
    private lateinit var btnIncome: Button
    private lateinit var btnOutcome: Button

    private val presenter by inject<MainPresenter>()

    companion object {

        fun newIntent(ctx: Context): Intent {
            return Intent(ctx, MainActivity::class.java)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBasePresenter(presenter)

        btnIncome = findViewById(R.id.btn_income)
        btnOutcome = findViewById(R.id.btn_outcome)
        etValue = findViewById(R.id.et_value)

        btnIncome.setOnClickListener {
            var value = getValueAsFloat()
            presenter.onIncome(value)
        }

        btnOutcome.setOnClickListener {
            var value = getValueAsFloat()
            presenter.onOutcome(value)
        }
    }

    override fun render(model : MainViewModel) {
        var strValue = df.format(model.value)
        etValue.setText(strValue)
    }

    override fun onResume() {
        super.onResume()
        presenter.init()
    }

    fun getValueAsFloat(): Float {
        val str = etValue.text.toString()
        return df.parse(str).toFloat()
    }

    override fun goToIncome(value : Float) {
        navigation.openDetailActivity(this, value, true)
    }

    override fun goToOutcome(value : Float) {
        navigation.openDetailActivity(this, value, false)
    }
}
