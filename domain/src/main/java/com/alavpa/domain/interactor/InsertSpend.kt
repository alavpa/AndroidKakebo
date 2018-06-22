package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Spend
import io.reactivex.Observable
import java.util.*

/**
 * Created by alex_avila on 3/11/17.
 */
class InsertSpend(private val repository: Repository) : UseCase<Long>(){
    var value: Float = 0f
    var isIncome : Boolean = false
    var categoryId : Long = -1

    override fun buildUseCase(): Observable<Long> {

        return repository.getCategory(categoryId)
                .flatMap { category ->
                    val spend = Spend(0, value, Date(), category, isIncome)
                    repository.insertSpend(spend)
                }.toObservable()

    }
}