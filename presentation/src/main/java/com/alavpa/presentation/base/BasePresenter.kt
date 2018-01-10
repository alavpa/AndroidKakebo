package com.alavpa.presentation.base

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * Created by alex_avila on 8/11/17.
 */
open class BasePresenter<T :BaseView> {

    var view : T? = null

    fun attachView(view: T){
        this.view = view
    }

    fun detachView(){
        this.view = null
    }

    fun execute(execute: () -> Unit,
                onSuccess: () -> Unit,
                onError: (Throwable) -> Unit) {

        launch(UI) {
            try {
                async { execute() }.await()
                onSuccess()
            } catch (t: Throwable) {
                onError(t)
            }
        }
    }
}