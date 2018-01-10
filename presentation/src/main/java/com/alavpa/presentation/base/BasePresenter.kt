package com.alavpa.presentation.base

import com.alavpa.domain.interactor.UseCase
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * Created by alex_avila on 8/11/17.
 */
open class BasePresenter<T :BaseView> (private vararg val useCases: UseCase<*> ){

    var view : T? = null

    fun attachView(view: T){
        this.view = view
    }

    fun detachView(){
        this.view = null
    }

    fun clearUseCases(){
        useCases.forEach { useCase -> useCase.dispose() }
    }
}