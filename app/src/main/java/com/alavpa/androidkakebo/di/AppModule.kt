package com.alavpa.androidkakebo.di

import com.alavpa.androidkakebo.navigation.Navigation
import org.koin.dsl.module.applicationContext;
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

/**
 * Created by alex on 05/01/2018.
 */
val appModule = applicationContext {

    factory { Navigation() }
    factory { createDecimalFormat() }
}

fun createDecimalFormat(): DecimalFormat {
    val decimalFormat = DecimalFormat()
    val dfs = DecimalFormatSymbols(Locale.getDefault())
    dfs.decimalSeparator = '.'
    dfs.groupingSeparator = ','

    decimalFormat.decimalFormatSymbols = dfs
    decimalFormat.maximumFractionDigits = 2
    decimalFormat.minimumFractionDigits = 2

    return decimalFormat;
}