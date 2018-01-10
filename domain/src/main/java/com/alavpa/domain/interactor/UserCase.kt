package com.alavpa.domain.interactor

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import java.util.*

/**
 * Created by alex on 10/01/2018.
 */
abstract class UseCase<T> {

    private lateinit var observable : Observable<T>

    private val disposables = CompositeDisposable()

    abstract fun buildUseCase(): Observable<T>

    fun execute(subscribeOn: Scheduler,
                observeOn: Scheduler,
                onSuccess: (T) -> Unit,
                onError: (Throwable) -> Unit) {

        observable = buildUseCase()

        val dispose = observable
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(onSuccess, onError)

        disposables.add(dispose)
    }

    fun dispose(){
        disposables.clear()
    }



}