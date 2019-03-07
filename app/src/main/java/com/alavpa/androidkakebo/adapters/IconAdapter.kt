package com.alavpa.androidkakebo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alavpa.androidkakebo.R

class IconAdapter(private val onItemClickCallback: (Int) -> Unit)
    : RecyclerView.Adapter<IconAdapter.ItemViewHolder>() {

    var items: List<Int> = listOf()
    var itemSelected = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_icon, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = items[position]
        holder.bind(
            item,
            itemSelected == item,
            View.OnClickListener {
                itemSelected = if (itemSelected != item) item else -1
                onItemClickCallback(itemSelected)
                notifyDataSetChanged()
            }
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        private var icon: ImageView = itemView.findViewById(R.id.icon)

        fun bind(item: Int, isSelected: Boolean, onClickListener: View.OnClickListener) {
            icon.setImageResource(item)
            itemView.isSelected = isSelected
            itemView.setOnClickListener(onClickListener)
        }
    }
}