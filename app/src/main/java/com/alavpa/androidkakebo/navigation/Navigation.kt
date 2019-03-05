package com.alavpa.androidkakebo.navigation

import android.app.Activity
import android.content.Intent
import com.alavpa.androidkakebo.ui.categories.CategoryActivity

class Navigation {

    fun openCategories(context: Activity?) {
        context?.let {
            val intent = Intent(it, CategoryActivity::class.java)
            it.startActivity(intent)
        }
    }
}