package com.alavpa.androidkakebo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alavpa.androidkakebo.R
import com.alavpa.presentation.model.NotificationItem

class NotificationsAdapter(
    private val onClick: (Long, Boolean) -> Unit
) : RecyclerView.Adapter<NotificationsAdapter.ItemViewHolder>() {

    var items: List<NotificationItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notifications, parent, false)
        return ItemViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(
        itemView: View,
        private val onClick: (Long, Boolean) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private var notificationCheck: CheckedTextView = itemView.findViewById(R.id.notification_check)

        fun bind(item: NotificationItem) {
            notificationCheck.text = item.text
            if (item.selected) setEnabled(notificationCheck) else setDisabled(notificationCheck)

            notificationCheck.setOnClickListener {
                with(it as CheckedTextView) {
                    if (isChecked) {
                        setDisabled(this)
                        item.selected = false
                        onClick(item.id, false)
                    } else {
                        setEnabled(this)
                        item.selected = true
                        onClick(item.id, true)
                    }
                }
            }
        }

        private fun setEnabled(view: CheckedTextView) {
            view.isChecked = true
            view.checkMarkDrawable = ContextCompat.getDrawable(itemView.context, R.drawable.ic_check_black_24dp)
        }

        private fun setDisabled(view: CheckedTextView) {
            view.isChecked = false
            view.checkMarkDrawable = null
        }
    }
}