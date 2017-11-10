package com.alavpa.domain.interactor

/**
 * Created by alex_avila on 3/11/17.
 */
class GetCategories {
    var isIncome = false

    fun run(): List<String> {
        try {

            return if(isIncome){
                listOf("Work", "Inheritance")
            }else {
                listOf("Home", "Fun", "Transport")
            }

        } catch (e: Throwable) {
            throw e
        }
    }
}