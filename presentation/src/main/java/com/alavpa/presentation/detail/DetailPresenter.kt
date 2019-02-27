package com.alavpa.presentation.detail

import com.alavpa.domain.entity.Category
import com.alavpa.domain.interactor.GetCategories
import com.alavpa.domain.interactor.InsertCategory
import com.alavpa.domain.interactor.InsertSpend
import com.alavpa.presentation.base.BasePresenter
import com.alavpa.presentation.mapper.ViewMapper

class DetailPresenter(private val insertSpend: InsertSpend,
                      private val getCategories: GetCategories,
                      private val insertCategory: InsertCategory,
                      private val mapper: ViewMapper)
    : BasePresenter<DetailView>(insertSpend, getCategories, insertCategory) {

    var value = 0f
    var isIncome = false
    private var categoryItems: List<CategoryItem> = listOf()
    private var selectedCategory = -1L

    fun onItemClick(position: Long) {
        this.selectedCategory = position
    }

    fun subscribeCategories() {
        getCategories.isIncome = isIncome

        try{
            val list = getCategories.buildUseCase()
            populateCategories(list)
        }catch (throwable: Throwable){
            view?.showError("Error: " + throwable.message)
        }
    }

    private fun populateCategories(categories: List<Category>) {
        this.categoryItems = categories.map { category -> mapper.entityToView(category) }
        view?.populateCategories(this.categoryItems, selectedCategory)
    }

    fun cancel(){
        view?.onCancel()
    }

    fun done() {
        insertSpend.value = value
        insertSpend.isIncome = isIncome
        insertSpend.categoryId = selectedCategory

        try {
            insertSpend.buildUseCase()
            view?.onDone()
        }catch (throwable: Throwable){
            view?.showError("Error: " + throwable.message)
        }
    }

    fun add(name: String) {
        insertCategory.category = Category(0, name, isIncome)

        try{
            insertCategory.buildUseCase()
        }catch (throwable: Throwable){
            view?.showError("Error: " + throwable.message)
        }
    }
}
