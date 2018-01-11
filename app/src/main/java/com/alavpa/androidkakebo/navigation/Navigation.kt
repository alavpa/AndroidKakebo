package com.alavpa.androidkakebo.navigation

import android.content.Context
import com.alavpa.androidkakebo.ui.detail.DetailActivity
import com.alavpa.androidkakebo.ui.main.MainActivity

/**
 * Created by alex_avila on 3/11/17.
 */
class Navigation {

    fun openMainActivity(context: Context) {

        val intent = MainActivity.newIntent(context)
        context.startActivity(intent)

    }

    fun openDetailActivity(context:Context, value : Float, isIncome : Boolean){
        val intent  = DetailActivity.newIntent(context,value,isIncome)
        context.startActivity(intent)
    }

}