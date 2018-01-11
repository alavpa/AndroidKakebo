package com.alavpa.androidkakebo.navigation

import android.app.Activity
import android.content.Context
import com.alavpa.androidkakebo.ui.detail.DetailActivity
import com.alavpa.androidkakebo.ui.main.MainActivity

/**
 * Created by alex_avila on 3/11/17.
 */
class Navigation {

    companion object {
        const val DETAIL_ACTIVITY_REQUESTCODE = 1
    }
    fun openMainActivity(context: Activity) {

        val intent = MainActivity.newIntent(context)
        context.startActivity(intent)

    }

    fun openDetailActivity(context:Activity, value : Float, isIncome : Boolean){
        val intent  = DetailActivity.newIntent(context,value,isIncome)
        context.startActivityForResult(intent, DETAIL_ACTIVITY_REQUESTCODE)
    }

}