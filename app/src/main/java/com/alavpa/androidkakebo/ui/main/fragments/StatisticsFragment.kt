package com.alavpa.androidkakebo.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.alavpa.androidkakebo.R
import kotlinx.android.synthetic.main.fragment_statistics.pie1
import kotlinx.android.synthetic.main.fragment_statistics.pie2

class StatisticsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            pie1?.color = ContextCompat.getColor(it, R.color.colorPrimary)
            pie2?.color = ContextCompat.getColor(it, R.color.red)
        }

        pie1?.angle = 100f
        pie2?.angle = 200f

        pie1?.strokeSize = activity?.resources?.getDimension(R.dimen.font_small) ?: 0f
        pie2?.strokeSize = activity?.resources?.getDimension(R.dimen.font_small) ?: 0f

        pie1.textSize = activity?.resources?.getDimension(R.dimen.font_small) ?: 0f
        pie2.textSize = activity?.resources?.getDimension(R.dimen.font_small) ?: 0f

        pie1?.amount = "12€"
        pie2?.amount = "124€"
    }
}