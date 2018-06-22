package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Spend
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by alex_avila on 3/11/17.
 */
class InsertCategory(private val repository: Repository) : UseCase<Long>(){
    lateinit var category: Category

    override fun buildUseCase(): Observable<Long> {
        return repository.insertCategory(category).toObservable()
    }
}