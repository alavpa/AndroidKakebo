package com.alavpa.presentation.home

import com.alavpa.domain.interactor.GetCategories
import com.alavpa.presentation.base.BasePresenter

class HomePresenter(private val getCategories: GetCategories) : BasePresenter<HomeView>() {

    fun loadCategories() {
        getCategories.perform(
            { list ->
                view?.populateCategories(list)
            },
            {
                view?.showError(it.message)
            }
        )
    }
}
