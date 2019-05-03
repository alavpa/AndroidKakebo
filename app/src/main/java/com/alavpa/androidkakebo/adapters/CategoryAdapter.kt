package com.alavpa.androidkakebo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alavpa.androidkakebo.R
import com.alavpa.domain.entity.Category

class CategoryAdapter(
    private val isEditable: Boolean = false,
    private val onItemClickCallback: (Long) -> Unit = {},
    private val onItemDelete: (Category) -> Unit = {}
) : RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>() {

    var items: List<Category> = listOf()
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
            isEditable,
            View.OnClickListener {
                onItemDelete(item)
                notifyDataSetChanged()
            },
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

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var name: TextView = itemView.findViewById(R.id.name)
        private var icon: ImageView = itemView.findViewById(R.id.icon)
        private var type: ImageView = itemView.findViewById(R.id.type)
        private var delete: ImageView = itemView.findViewById(R.id.delete)

        fun bind(
            item: Category,
            isSelected: Boolean,
            isEditable: Boolean,
            onClickDeleteListener: View.OnClickListener,
            onClickListener: View.OnClickListener
        ) {
            name.text = item.name
            val trending = if (item.income) R.drawable.ic_round_trending_up_24px
            else R.drawable.ic_round_trending_down_24px
            type.setImageResource(trending)
            icon.setImageResource(item.icon)
            delete.visibility = if (isEditable) View.VISIBLE else View.GONE
            delete.setOnClickListener(onClickDeleteListener)
            itemView.isSelected = isSelected
            itemView.setOnClickListener(onClickListener)
        }
    }
}