package com.alavpa.presentation.base

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

    fun <T> UseCase<T>.perform(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit) {
        val useCase = this
        GlobalScope.launch {
            try {
                val res = useCase.execute()
                GlobalScope.launch(Dispatchers.Main) {
                    onSuccess(res)
                }
            } catch (t: Throwable) {
                GlobalScope.launch(Dispatchers.Main) {
                    onError(t)
                }
            }
        }
    }
}