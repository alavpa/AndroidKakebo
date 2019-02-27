package com.alavpa.presentation.base

/**
 * Created by alex_avila on 8/11/17.
 */
interface BaseView {
    fun startLoading(message : String)
    fun stopLoading()
    fun showError(message : String?)
}