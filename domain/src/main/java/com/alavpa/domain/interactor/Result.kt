package com.alavpa.domain.interactor

class Result<T>(
    val onSuccess: (T) -> Unit = {},
    val onError: (Throwable) -> Unit = {})