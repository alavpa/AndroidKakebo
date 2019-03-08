package com.alavpa.presentation.categories.add

import com.alavpa.domain.entity.Category
import com.alavpa.domain.interactor.GetIcons
import com.alavpa.domain.interactor.InsertCategory
import com.alavpa.presentation.base.BasePresenter

class AddCategoryPresenter(
    private val getIcons: GetIcons,
    private val insertCategory: InsertCategory
) : BasePresenter<AddCategoryView>() {

    fun save(icon: Int, text: String?, type: Int?) {
        insertCategory.category = Category(icon = icon, name = text ?: "", income = type == 2)
        insertCategory.perform {
            view?.categorySaved()
        }
    }

    fun loadItems() {
        getIcons.perform {
            view?.populateIcons(it)
        }
    }
}