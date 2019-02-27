package com.alavpa.presentation.base

import com.alavpa.domain.interactor.UseCase

open class BasePresenter<T :BaseView> (private vararg val useCases: UseCase<*> ){

    var view : T? = null

    fun attachView(view: T){
        this.view = view
    }

    fun detachView(){
        this.view = null
    }
}