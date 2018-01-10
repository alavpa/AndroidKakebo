package com.alavpa.presentation.detail

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Spend
import com.alavpa.domain.interactor.GetCategories
import com.alavpa.domain.interactor.InsertSpend
import com.alavpa.presentation.base.BasePresenter
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.sql.DataSource

/**
 * Created by alex_avila on 8/11/17.
 */
class DetailPresenter(private val insertSpend: InsertSpend) : BasePresenter<DetailView>() {


    private val model = DetailViewModel()
    var value = 0f
    var isIncome = false

    fun onItemClick(position: Int) {
        model.selectedCategory = position
    }

    fun init() {

    }

    fun done() {

        insertSpend.spend = Spend(0,
                value,
                Date(),
                Category(0, "category", isIncome),
                isIncome)

        var disposable = insertSpend.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ id -> view?.showError("Added:" + id) },
                        { throwable -> view?.showError("Error: " + throwable.message) })

        disposables.add(disposable)
    }
}