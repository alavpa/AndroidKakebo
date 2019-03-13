package com.alavpa.presentation.statistics

import com.alavpa.presentation.base.BaseView

interface StatisticsView : BaseView {
    fun setIncomeAmount(amount: Float)
    fun setOutcomeAmount(amount: Float)
    fun setOutcomeAngle(angle: Float)
    fun setIncomeAngle(angle: Float)
}