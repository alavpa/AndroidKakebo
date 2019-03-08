package com.alavpa.androidkakebo.navigation

import android.app.Activity
import android.content.Intent
import com.alavpa.androidkakebo.ui.categories.CategoryActivity
import com.alavpa.androidkakebo.ui.categories.add.AddCategoryActivity
import com.alavpa.androidkakebo.ui.transactions.TransactionActivity

class Navigation {

    fun openCategories(context: Activity?) {
        context?.let {
            val intent = Intent(it, CategoryActivity::class.java)
            it.startActivity(intent)
        }
    }

    fun openAddCategory(context: Activity?) {
        context?.let {
            val intent = Intent(it, AddCategoryActivity::class.java)
            it.startActivity(intent)
        }
    }

    fun openTransaction(context: Activity?) {
        context?.let {
            val intent = Intent(it, TransactionActivity::class.java)
            it.startActivity(intent)
        }
    }
}