package com.alavpa.presentation.mapper

import com.alavpa.domain.entity.Category
import com.alavpa.presentation.detail.CategoryItem

/**
 * Created by alex on 11/01/2018.
 */
class ViewMapper{

    fun entityToView(entity : Category) :CategoryItem = CategoryItem(entity.id,entity.name)
}