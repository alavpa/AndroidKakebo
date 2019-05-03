package com.alavpa.data.resources

import android.content.Context
import com.alavpa.data.R

class ResourcesDataSource(private val context: Context) {

    fun getIcons(): List<Int> {
        val list = mutableListOf<Int>()
        val array = context.resources.obtainTypedArray(R.array.icons)
        for (id in 0 until array.length()) {
            list.add(array.getResourceId(id, 0))
        }
        array.recycle()
        return list
    }

    fun getNotificationsList(): List<String> {
        return context.resources.getStringArray(R.array.notifications).toList()
    }
}