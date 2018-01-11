package com.alavpa.domain.interactor

import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Category
import io.reactivex.Observable
import java.util.*

/**
 * Created by alex_avila on 3/11/17.
 */
class GetCategory(private val repository: Repository) : UseCase<Category>(){

    var id = -1L

    override fun buildUseCase(): Observable<Category> {
        return repository.getCategory(id).toObservable()
    }
}