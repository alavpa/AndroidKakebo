package com.alavpa.domain.entity

import java.util.*

/**
 * Created by alex_avila on 3/11/17.
 */
class Spend (val id : Long = 0,
        val value : Float,
             val date : Date,
             val category : Category,
             val income : Boolean)