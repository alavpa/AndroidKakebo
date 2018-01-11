package com.alavpa.presentation.detail

import com.alavpa.presentation.base.BaseView

/**
 * Created by alex_avila on 8/11/17.
 */
interface DetailView : BaseView{

    fun finish()
    fun populateCategories(categories: List<String>, selectedCategory : Int)
}