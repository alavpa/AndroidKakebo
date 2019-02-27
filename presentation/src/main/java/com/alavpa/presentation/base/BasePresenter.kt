package com.alavpa.presentation.base

import com.alavpa.domain.interactor.Result
import com.alavpa.domain.interactor.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class BasePresenter<T : BaseView>(private vararg val useCases: UseCase<*>) {

    var view: T? = null

    fun attachView(view: T) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun <T> UseCase<T>.exec(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit) {
        this.execute(
            Result(
                {
                    GlobalScope.launch(Dispatchers.Main) {
                        onSuccess(it)
                    }
                },
                {
                    GlobalScope.launch(Dispatchers.Main) {
                        onError(it)
                    }
                }
            )
        )
    }
}