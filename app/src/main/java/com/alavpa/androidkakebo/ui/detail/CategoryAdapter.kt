package com.alavpa.androidkakebo.ui.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alavpa.androidkakebo.R
import com.alavpa.presentation.detail.CategoryItem

/**
 * Created by alex_avila on 9/11/17.
 */
class CategoryAdapter(private val onItemClickCallback: (Long)->Unit)
    : RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>() {

    var items: List<CategoryItem> = listOf()
    var itemSelected = -1L

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_category,parent,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {

        val item = items[position]
        holder?.bind(item,
                itemSelected == item.id,
                View.OnClickListener {

                    itemSelected = if(itemSelected!=item.id) item.id else -1

                    onItemClickCallback(itemSelected)
                    notifyDataSetChanged()
                })
    }


    override fun getItemCount(): Int {
      return items.size
    }

    class ItemViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        private var tvName: TextView = itemView.findViewById(R.id.tv_name)

        fun bind(item: CategoryItem, isSelected: Boolean, onClickListener : View.OnClickListener) {
            tvName.text = item.name
            itemView.isSelected = isSelected
            itemView.setOnClickListener(onClickListener)
        }
    }
}