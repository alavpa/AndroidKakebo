package com.alavpa.androidkakebo.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alavpa.androidkakebo.R

/**
 * Created by alex_avila on 9/11/17.
 */
class CategoryAdapter(private val onItemClickCallback: (Int)->Unit)
    : RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>() {

    var items: List<String> = listOf()
    var itemSelected = -1

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_category,parent,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        holder?.bind(items[position],
                itemSelected == position,
                View.OnClickListener {

                    itemSelected = if(itemSelected!=position) position else -1

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

        fun bind(name: String, isSelected: Boolean, onClickListener : View.OnClickListener) {
            tvName.text = name
            itemView.isSelected = isSelected
            itemView.setOnClickListener(onClickListener)
        }
    }
}