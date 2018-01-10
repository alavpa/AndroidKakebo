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
//        getCategories.isIncome = isIncome

//        execute({ view.startLoading("loading") },
//                {
//                    var list =  getCategories.run()
//                    model.categories = list
//                },
//                { view.render(model) },
//                { t -> view.showError(t.message!!) },
//                { view.stopLoading() })
    }

    fun done() {
//        execute({ view.startLoading("loading") },
//                {
//                    var name = model.categories[model.selectedCategory]
//                    insertSpend.spend = Spend(value, Date(), Category(name,isIncome),isIncome)
//                    insertSpend.run()
//                },
//                { view.render(model) },
//                { t -> view.showError(t.message!!) },
//                {
//                    view.stopLoading()
//                    view.finish()
//                })

        insertSpend.spend = Spend(0,
                value,
                Date(),
                Category(0,"category",isIncome),
                isIncome)

        insertSpend.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Long>{
                    override fun onError(e: Throwable) {
                        view?.showError("not success")
                    }

                    override fun onSuccess(t: Long) {
                        view?.showError("success")
                    }

                    override fun onSubscribe(d: Disposable) {

                    }
                })
    }
}