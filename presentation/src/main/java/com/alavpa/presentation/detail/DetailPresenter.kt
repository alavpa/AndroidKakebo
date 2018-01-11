package com.alavpa.presentation.detail

import android.view.View
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Spend
import com.alavpa.domain.interactor.GetCategories
import com.alavpa.domain.interactor.InsertCategory
import com.alavpa.domain.interactor.InsertSpend
import com.alavpa.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by alex_avila on 8/11/17.
 */
class DetailPresenter(private val insertSpend: InsertSpend,
                      private val getCategories: GetCategories,
                      private val insertCategory: InsertCategory)
    : BasePresenter<DetailView>(insertSpend, getCategories, insertCategory) {

    var value = 0f
    var isIncome = false
    var categories : List<String> = listOf()
    var selectedCategory = -1

    fun onItemClick(position: Int) {
        this.selectedCategory = position
    }

    fun getCategories() {
        getCategories.isIncome = isIncome

        getCategories.execute(Schedulers.io(),
                AndroidSchedulers.mainThread(),
                { list ->
                    this.categories = list.map { category -> category.name }
                    view?.populateCategories(categories, selectedCategory)
                },
                { throwable -> view?.showError("Error: " + throwable.message) })
    }

    fun done() {

        insertSpend.spend = Spend(0,
                value,
                Date(),
                Category(0, "category", isIncome),
                isIncome)

        insertSpend.execute(Schedulers.io(),
                AndroidSchedulers.mainThread(),
                { id -> view?.showError("Added:" + id) },
                { throwable -> view?.showError("Error: " + throwable.message) })

    }

    fun add(name: String) {
        insertCategory.category = Category(0, name, isIncome)

        insertCategory.execute(Schedulers.io(), AndroidSchedulers.mainThread(),
                { id ->
                    view?.showError("Added: " + id)
                    getCategories()
                },
                { throwable -> view?.showError("Error: " + throwable.message) })
    }

    fun init() {
        getCategories()
    }
}
