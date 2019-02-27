package com.alavpa.androidkakebo.di

import com.alavpa.androidkakebo.navigation.Navigation
import org.koin.dsl.module.module
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

val appModule = module {

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

    return decimalFormat
}