package com.alavpa.domain.entity

data class Notification(val id: Long, val text: String, val selected: Boolean) {
    companion object {
        const val FIRST_DAY_ID = 1L
        const val AUTO_ID = 2L
        const val TARGET_ID = 3L
    }
}