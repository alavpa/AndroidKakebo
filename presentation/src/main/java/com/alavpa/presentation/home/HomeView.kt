package com.alavpa.presentation.home

import com.alavpa.domain.entity.Category
import com.alavpa.presentation.base.BaseView

interface HomeView : BaseView {
    fun populateCategories(list: List<Category>)

}