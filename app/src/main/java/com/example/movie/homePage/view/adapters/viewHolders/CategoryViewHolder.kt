package com.example.movie.homePage.view.adapters.viewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txvTitle: TextView = itemView.findViewById(R.id.txv_title)
    val recItems: RecyclerView = itemView.findViewById(R.id.rec_horizontal)
}