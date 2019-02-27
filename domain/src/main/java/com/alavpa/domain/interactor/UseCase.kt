package com.alavpa.domain.interactor

abstract class UseCase<T> {
    abstract fun buildUseCase(): T
}