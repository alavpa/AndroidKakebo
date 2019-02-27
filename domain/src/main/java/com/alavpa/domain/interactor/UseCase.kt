package com.alavpa.domain.interactor

abstract class UseCase<T> {
    abstract fun execute(result: Result<T>)
}