package com.alavpa.presentation.categories

import com.alavpa.domain.entity.Category
import com.alavpa.presentation.base.BaseView

interface CategoryView: BaseView {
    fun showEmptyList()
    fun populateCategories(list: List<Category>)
    fun openAddCategory()
}