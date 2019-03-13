package com.alavpa.presentation.statistics

import com.alavpa.domain.interactor.GetIncomeFromDate
import com.alavpa.domain.interactor.GetOutcomeFromDate
import com.alavpa.domain.interactor.GetTransactionsFromDate
import com.alavpa.presentation.base.BasePresenter
import java.util.Calendar

class StatisticsPresenter(
    private val getIncomeFromDate: GetIncomeFromDate,
    private val getOutcomeFromDate: GetOutcomeFromDate,
    private val getTransactionsFromDate: GetTransactionsFromDate
) : BasePresenter<StatisticsView>() {

    fun getIncome() {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_MONTH, 1)
        getIncomeFromDate.from = cal.timeInMillis

        getIncomeFromDate.perform { list ->
            val sum: Double = list.sumByDouble { it.amount.toDouble() }
            view?.setIncomeAmount(sum.toFloat())

            getTransactionsFromDate.perform { listAll->
                val sumTotal = listAll.sumByDouble { it.amount.toDouble() }.toFloat()
                val angle = (sum / sumTotal) * 360

                view?.setIncomeAngle(angle.toFloat())
            }
        }
    }

    fun getOutcome() {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_MONTH, 1)
        getOutcomeFromDate.from = cal.timeInMillis
        getTransactionsFromDate.from = cal.timeInMillis

        getOutcomeFromDate.perform { list ->
            val sum: Double = list.sumByDouble { it.amount.toDouble() }
            view?.setOutcomeAmount(sum.toFloat())

            getTransactionsFromDate.perform {listAll->
                val sumTotal = listAll.sumByDouble { it.amount.toDouble() }.toFloat()
                val angle = (sum / sumTotal) * 360

                view?.setOutcomeAngle(angle.toFloat())
            }
        }
    }
}