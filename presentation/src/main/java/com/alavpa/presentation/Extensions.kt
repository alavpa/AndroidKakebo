package com.alavpa.presentation

import com.alavpa.domain.entity.Category
import com.alavpa.presentation.categories.CategoryItem

fun Category.toViewModel(): CategoryItem {
    return CategoryItem(this.id, this.name, this.icon)
}