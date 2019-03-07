package com.alavpa.presentation.base

import com.alavpa.domain.interactor.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BasePresenter<T : BaseView> {

    var view: T? = null
    private val job = SupervisorJob()

    fun attachView(view: T) {
        this.view = view
    }

    open fun detachView() {
        job.cancel()
        this.view = null
    }

    fun <T> UseCase<T>.perform(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit) {
        val useCase = this
        CoroutineScope(Dispatchers.Default).launch(job) {
            try {
                coroutineScope {
                    val res = async { useCase.execute() }
                    withContext(Dispatchers.Main) {
                        onSuccess(res.await())
                    }
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main) {
                    onError(t)
                }
            }
        }
    }

    fun zip(useCases: List<UseCase<Any>>, onSuccess: (List<Any>) -> Unit, onError: (Throwable) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch(job) {
            try {
                coroutineScope {

                    val results = useCases.map {
                        async { it.execute() }
                    }

                    withContext(Dispatchers.Main) {
                        onSuccess(results.map { it.await() })
                    }
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main) {
                    onError(t)
                }
            }
        }
    }
}