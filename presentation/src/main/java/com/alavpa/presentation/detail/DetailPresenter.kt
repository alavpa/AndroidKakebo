package com.alavpa.presentation.detail

import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Spend
import com.alavpa.domain.interactor.GetCategories
import com.alavpa.domain.interactor.InsertSpend
import com.alavpa.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by alex_avila on 8/11/17.
 */
class DetailPresenter(private val insertSpend: InsertSpend,
                      private val getCategories: GetCategories)
    : BasePresenter<DetailView>(insertSpend,getCategories) {


    private val model = DetailViewModel()
    var value = 0f
    var isIncome = false

    fun onItemClick(position: Int) {
        model.selectedCategory = position
    }

    fun populateCategories() {
        getCategories.isIncome = isIncome

        getCategories.execute(Schedulers.io(),
                AndroidSchedulers.mainThread(),
                { list ->
                    model.categories = list.map { category -> category.name }
                    view?.render(model)
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
}
