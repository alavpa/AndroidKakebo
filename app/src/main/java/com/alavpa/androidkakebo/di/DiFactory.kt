package com.alavpa.androidkakebo.di

import com.alavpa.androidkakebo.Navigation
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

/**
 * Created by alex_avila on 8/11/17.
 */
class DiFactory {
    companion object {
        val instance: DiFactory by lazy {
            DiFactory()
        }
    }

    val navigation = Navigation()
    val decimalFormat = DecimalFormat()

    init {
        val dfs = DecimalFormatSymbols(Locale.getDefault())
        dfs.decimalSeparator = '.'
        dfs.groupingSeparator = ','

        decimalFormat.decimalFormatSymbols = dfs
        decimalFormat.maximumFractionDigits = 2
        decimalFormat.minimumFractionDigits = 2
    }
}