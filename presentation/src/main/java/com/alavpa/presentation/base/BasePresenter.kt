package com.alavpa.presentation.base

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * Created by alex_avila on 8/11/17.
 */
open class BasePresenter {

    fun execute(onStart: () -> Unit,
                execute: () -> Unit,
                onSuccess: () -> Unit,
                onException: (Throwable) -> Unit,
                onFinish: () -> Unit) {

        launch(UI) {

            onStart()

            try {
                async { execute() }.await()
                onSuccess()
            } catch (t: Throwable) {
                onException(t)
            }

            onFinish()
        }
    }
}