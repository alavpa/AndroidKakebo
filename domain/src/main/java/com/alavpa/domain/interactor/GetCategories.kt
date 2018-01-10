package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Category
import io.reactivex.Observable
import java.util.*

/**
 * Created by alex_avila on 3/11/17.
 */
class GetCategories(private val repository: Repository) : UseCase<List<Category>>(){

    var isIncome = false

    override fun buildUseCase(): Observable<List<Category>> {
        return repository.getCategories(isIncome).toObservable()
    }
}