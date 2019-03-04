package com.alavpa.presentation.detail

import com.alavpa.domain.entity.Category
import com.alavpa.domain.interactor.GetCategories
import com.alavpa.domain.interactor.InsertCategory
import com.alavpa.domain.interactor.InsertTransaction
import com.alavpa.presentation.base.BasePresenter
import com.alavpa.presentation.mapper.ViewMapper

class DetailPresenter(private val insertTransaction: InsertTransaction,
                      private val getCategories: GetCategories,
                      private val insertCategory: InsertCategory,
                      private val mapper: ViewMapper)
    : BasePresenter<DetailView>(insertTransaction, getCategories, insertCategory) {

    var value = 0f
    var isIncome = false
    private var categoryItems: List<CategoryItem> = listOf()
    private var selectedCategory = -1L

    fun onItemClick(position: Long) {
        this.selectedCategory = position
    }

    fun subscribeCategories() {
        getCategories.isIncome = isIncome
        getCategories.perform(::populateCategories, ::showError)
    }

    private fun populateCategories(categories: List<Category>) {
        this.categoryItems = categories.map { category -> mapper.entityToView(category) }
        view?.populateCategories(this.categoryItems, selectedCategory)
    }

    private fun showError(throwable: Throwable) {
        view?.showError(throwable.message)
    }

    fun cancel() {
        view?.onCancel()
    }

    fun done() {
        insertTransaction.value = value
        insertTransaction.isIncome = isIncome
        insertTransaction.categoryId = selectedCategory
        insertTransaction.perform({view?.onDone()}, ::showError)
    }

    fun add(name: String) {
        insertCategory.category = Category(0, name, isIncome)
        insertCategory.perform({view?.onDone()}, ::showError)
    }
}
