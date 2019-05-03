package com.alavpa.androidkakebo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alavpa.androidkakebo.R
import com.alavpa.domain.entity.Notification

class NotificationsAdapter : RecyclerView.Adapter<NotificationsAdapter.ItemViewHolder>() {

    var items: List<Notification> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notifications, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var notificationCheck: CheckedTextView = itemView.findViewById(R.id.notification_check)

        fun bind(item: Notification) {
            notificationCheck.text = item.text
            notificationCheck.setOnClickListener {
                with(it as CheckedTextView) {
                    if (isChecked) {
                        isChecked = false
                        checkMarkDrawable = null
                    } else {
                        isChecked = true
                        checkMarkDrawable = ContextCompat.getDrawable(itemView.context, R.drawable.ic_check_black_24dp)
                    }
                }

            }
        }
    }
}