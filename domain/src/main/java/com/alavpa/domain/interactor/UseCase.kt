package com.alavpa.domain.interactor

abstract class UseCase<T> {
    abstract suspend fun execute(): T
}