package com.alavpa.presentation.detail

import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Spend
import com.alavpa.domain.interactor.GetCategories
import com.alavpa.domain.interactor.InsertSpend
import com.alavpa.presentation.base.BasePresenter
import java.util.*

/**
 * Created by alex_avila on 8/11/17.
 */
class DetailPresenter(val view: DetailView) : BasePresenter() {

    private val insertSpend = InsertSpend()
    private val getCategories = GetCategories()

    private val model = DetailViewModel()
    var value = 0f
    var isIncome = false

    fun onItemClick(position: Int) {
        model.selectedCategory = position
    }

    fun init() {
        getCategories.isIncome = isIncome

        execute({ view.startLoading("loading") },
                {
                    var list =  getCategories.run()
                    model.categories = list
                },
                { view.render(model) },
                { t -> view.showError(t.message) },
                { view.stopLoading() })
    }

    fun done() {
        execute({ view.startLoading("loading") },
                {
                    var name = model.categories[model.selectedCategory]
                    insertSpend.spend = Spend(value, Date(), Category(name,isIncome),isIncome)
                    insertSpend.run()
                },
                { view.render(model) },
                { t -> view.showError(t.message) },
                {
                    view.stopLoading()
                    view.finish()
                })
    }

    fun cancel(){
        view.finish()
    }
}