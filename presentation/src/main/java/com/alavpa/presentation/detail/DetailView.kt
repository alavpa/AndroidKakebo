package com.alavpa.presentation.detail

import com.alavpa.presentation.base.BaseView

/**
 * Created by alex_avila on 8/11/17.
 */
interface DetailView : BaseView{

    fun populateCategories(categories: List<CategoryItem>, selectedCategory : Long)
    fun onDone()
    fun onCancel()
}