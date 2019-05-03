package com.alavpa.data.preferences

import android.content.SharedPreferences

class PreferencesDataSource(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val KEY_FIRST_DAY_ENABLED = "KEY_FIRST_DAY_ENABLED"
        private const val KEY_TARGET_ENABLED = "KEY_TARGET_ENABLED"
        private const val KEY_AUTO_ENABLED = "KEY_AUTO_ENABLED"
    }

    fun setFirstDayEnabled(isEnabled: Boolean) = save(KEY_FIRST_DAY_ENABLED, isEnabled)

    fun getFirstDayEnabled() = get(KEY_FIRST_DAY_ENABLED, true)

    fun setTargetAchievedEnabled(isEnabled: Boolean) = save(KEY_TARGET_ENABLED, isEnabled)

    fun getTargetAchievedEnabled() = get(KEY_TARGET_ENABLED, true)

    fun setTransactionsAutoEnabled(isEnabled: Boolean) = save(KEY_AUTO_ENABLED, isEnabled)

    fun getTransactionsAutoEnabled() = get(KEY_AUTO_ENABLED, true)

    private fun <T> save(key: String, value: T) {
        when (value) {
            is Long -> sharedPreferences.edit().putLong(key, value).apply()
            is String -> sharedPreferences.edit().putString(key, value).apply()
            is Int -> sharedPreferences.edit().putInt(key, value).apply()
            is Boolean -> sharedPreferences.edit().putBoolean(key, value).apply()
        }
    }

    private inline fun <reified T> get(key: String, default: T): T {
        return when (default) {
            is Long -> sharedPreferences.getLong(key, default) as T
            is String -> sharedPreferences.getString(key, default) as T
            is Int -> sharedPreferences.getInt(key, default) as T
            is Boolean -> sharedPreferences.getBoolean(key, default) as T
            else -> default
        }
    }
}