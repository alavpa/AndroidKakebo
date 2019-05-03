package com.alavpa.androidkakebo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alavpa.androidkakebo.R
import com.alavpa.presentation.model.HistogramItem
import com.alavpa.androidkakebo.views.KakeboHistogram

class HistorialAdapter : RecyclerView.Adapter<HistorialAdapter.ItemViewHolder>() {

    var items: List<HistogramItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_historial, parent, false)
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

        private var histogram: KakeboHistogram = itemView.findViewById(R.id.histogram)
        private var year: TextView = itemView.findViewById(R.id.year)

        fun bind(item: HistogramItem) {
            year.text = item.title
            histogram.data = item.data
        }
    }
}