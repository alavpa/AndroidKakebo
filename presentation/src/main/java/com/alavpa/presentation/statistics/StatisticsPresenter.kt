package com.alavpa.presentation.statistics

import com.alavpa.domain.interactor.GetIncomeFromDate
import com.alavpa.domain.interactor.GetOutcomeFromDate
import com.alavpa.domain.interactor.GetTransactions
import com.alavpa.domain.interactor.GetTransactionsFromDate
import com.alavpa.presentation.base.BasePresenter
import com.alavpa.presentation.model.HistogramItem
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class StatisticsPresenter(
    private val getIncomeFromDate: GetIncomeFromDate,
    private val getOutcomeFromDate: GetOutcomeFromDate,
    private val getTransactionsFromDate: GetTransactionsFromDate,
    private val getTransactions: GetTransactions
) : BasePresenter<StatisticsView>() {

    private val calStart = Calendar.getInstance()
    private val calEnd = Calendar.getInstance()

    private val calLabel = Calendar.getInstance()
    private val simpleDateFormat = SimpleDateFormat("MMM", Locale("es", "ES"))

    fun getIncome() {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_MONTH, 1)
        getIncomeFromDate.from = cal.timeInMillis

        getIncomeFromDate.perform { list ->
            val sum: Double = list.sumByDouble { it.amount.toDouble() }
            view?.setIncomeAmount(sum.toFloat())

            getTransactionsFromDate.perform { listAll ->
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

            getTransactionsFromDate.perform { listAll ->
                val sumTotal = listAll.sumByDouble { it.amount.toDouble() }.toFloat()
                val angle = (sum / sumTotal) * 360

                view?.setOutcomeAngle(angle.toFloat())
            }
        }
    }

    fun getHistogram() {

        getTransactions.perform { transactions ->

            if (transactions.isNotEmpty()) {
                val items = mutableListOf<HistogramItem>()
                calStart.time = transactions.firstOrNull()?.insertDate
                calEnd.time = transactions.lastOrNull()?.insertDate
                val yearMin = calStart.get(Calendar.YEAR)
                val monthMin = calStart.get(Calendar.MONTH)
                val yearMax = calEnd.get(Calendar.YEAR)
                val monthMax = calEnd.get(Calendar.MONTH)

                for (year in yearMin..yearMax) {
                    val data = mutableListOf<Pair<String, Float>>()
                    val fromMonth = if (year == yearMin) monthMin else Calendar.JANUARY
                    val toMonth = if (year == yearMax) monthMax else Calendar.DECEMBER

                    for (month in fromMonth..toMonth) {
                        val sumIncome = transactions.filter {
                            val cal1 = Calendar.getInstance()
                            val m = cal1.get(Calendar.MONTH)
                            val y = cal1.get(Calendar.YEAR)
                            m == month && y == year && it.category.income
                        }.sumByDouble { it.amount.toDouble() }

                        val sumOutcome = transactions.filter {
                            val cal1 = Calendar.getInstance()
                            val m = cal1.get(Calendar.MONTH)
                            val y = cal1.get(Calendar.YEAR)
                            m == month && y == year && !it.category.income
                        }.sumByDouble { it.amount.toDouble() }

                        val sum = sumIncome - sumOutcome

                        calLabel.set(Calendar.MONTH, month)
                        val monthStr = simpleDateFormat.format(calLabel.time)
                        data.add(Pair(monthStr, sum.toFloat()))
                    }

                    items.add(HistogramItem(year.toString(), data))
                }

                view?.populateHistogram(items)
            }
        }
    }
}