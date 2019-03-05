package com.alavpa.androidkakebo.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alavpa.androidkakebo.R
import com.alavpa.presentation.categories.CategoryItem

class CategoryAdapter(private val onItemClickCallback: (Long) -> Unit)
    : RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>() {

    var items: List<CategoryItem> = listOf()
    var itemSelected = -1L

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = items[position]
        holder.bind(
            item,
            itemSelected == item.id,
            View.OnClickListener {
                itemSelected = if (itemSelected != item.id) item.id else -1
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

        private var name: TextView = itemView.findViewById(R.id.name)
        private var icon: ImageView = itemView.findViewById(R.id.icon)

        fun bind(item: CategoryItem, isSelected: Boolean, onClickListener: View.OnClickListener) {
            name.text = item.name
            icon.setImageResource(item.icon)
            itemView.isSelected = isSelected
            itemView.setOnClickListener(onClickListener)
        }
    }
}