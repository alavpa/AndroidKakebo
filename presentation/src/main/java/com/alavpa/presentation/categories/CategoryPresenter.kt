package com.alavpa.presentation.categories

import com.alavpa.domain.entity.Category
import com.alavpa.domain.interactor.DeleteCategory
import com.alavpa.domain.interactor.GetCategories
import com.alavpa.presentation.base.BasePresenter

class CategoryPresenter(
    private val getCategories: GetCategories,
    private val removeCategory: DeleteCategory
) : BasePresenter<CategoryView>() {

    fun getCategories() {
        getCategories.perform(::onGetCategories, ::onError)
    }

    private fun onGetCategories(list: List<Category>) {
        if (list.isEmpty()) {
            view?.showEmptyList()
        } else {
            view?.populateCategories(list)
        }
    }

    private fun onError(throwable: Throwable) {
        view?.showError(throwable.message)
    }

    fun onClickAdd() {
        view?.openAddCategory()
    }

    fun onClickItem(id: Long) {

    }

    fun onDeleteItem(category: Category) {
        removeCategory.category = category
        removeCategory.perform(
            {
                if (it > 0) {
                    getCategories()
                }
            },
            {
                view?.showError(it.message)
            }
        )
    }
}