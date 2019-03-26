package com.alavpa.androidkakebo.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.alavpa.androidkakebo.R
import com.alavpa.androidkakebo.adapters.HistorialAdapter
import com.alavpa.androidkakebo.adapters.TestAdapter
import com.alavpa.androidkakebo.base.BaseFragment
import com.alavpa.presentation.model.HistogramItem
import com.alavpa.presentation.statistics.StatisticsPresenter
import com.alavpa.presentation.statistics.StatisticsView
import kotlinx.android.synthetic.main.fragment_statistics.historial
import kotlinx.android.synthetic.main.fragment_statistics.pie1
import kotlinx.android.synthetic.main.fragment_statistics.pie2
import org.koin.android.ext.android.inject

class StatisticsFragment : BaseFragment<StatisticsPresenter>(), StatisticsView {

    private val presenter: StatisticsPresenter by inject()

    private val adapter = HistorialAdapter()

    override fun bindPresenter(): StatisticsPresenter {
        return presenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            pie1?.color = ContextCompat.getColor(it, R.color.colorPrimary)
            pie2?.color = ContextCompat.getColor(it, R.color.red)
        }

        pie1?.strokeSize = activity?.resources?.getDimension(R.dimen.font_small) ?: 0f
        pie2?.strokeSize = activity?.resources?.getDimension(R.dimen.font_small) ?: 0f

        pie1.textSize = activity?.resources?.getDimension(R.dimen.font_small) ?: 0f
        pie2.textSize = activity?.resources?.getDimension(R.dimen.font_small) ?: 0f

        historial?.layoutManager = LinearLayoutManager(activity)
        historial?.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.getIncome()
        presenter.getOutcome()
        presenter.getHistogram()
    }

    override fun setIncomeAmount(amount: Float) {
        pie1?.amount = "$amount€"
        pie1?.invalidate()
    }

    override fun setOutcomeAmount(amount: Float) {
        pie2?.amount = "$amount€"
        pie2?.invalidate()
    }

    override fun setOutcomeAngle(angle: Float) {
        pie2?.angle = angle
        pie2?.invalidate()
    }

    override fun setIncomeAngle(angle: Float) {
        pie1?.angle = angle
        pie1?.invalidate()
    }

    override fun populateHistogram(items: MutableList<HistogramItem>) {
        adapter.items = items
        adapter.notifyDataSetChanged()
    }
}