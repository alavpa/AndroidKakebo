package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Spend
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by alex_avila on 3/11/17.
 */
class InsertSpend(private val repository: Repository) : UseCase<Long>(){
    lateinit var spend: Spend

    override fun buildUseCase(): Observable<Long> {
        return repository.insertSpend(spend).toObservable()
    }
}