package com.alavpa.presentation.categories.add

import com.alavpa.domain.interactor.GetIcons
import com.alavpa.presentation.base.BasePresenter

class AddCategoryPresenter(private val getIcons: GetIcons) : BasePresenter<AddCategoryView>() {
    fun onClickItem(item: Int) {

    }

    fun save(text: String) {

    }

    fun loadItems() {

        getIcons.perform(onSuccess = {
            view?.populateIcons(it)
        })
    }
}