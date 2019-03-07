package com.alavpa.presentation.categories.add

import com.alavpa.presentation.base.BaseView

interface AddCategoryView : BaseView {
    fun populateIcons(icons: List<Int>)
}