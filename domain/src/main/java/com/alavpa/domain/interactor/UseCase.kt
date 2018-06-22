package com.alavpa.domain.interactor

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

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

        Timber.d("Disposables: " + disposables.size())
    }

    fun dispose(){
        Timber.d("Clear Disposables: " + disposables.size())
        disposables.clear()
    }
}