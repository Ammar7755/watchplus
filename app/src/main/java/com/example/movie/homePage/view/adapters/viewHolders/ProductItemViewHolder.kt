package com.example.movie.homePage.view.adapters.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R

class ProductItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txvItem: TextView = itemView.findViewById(R.id.txv_item)
    val imgItem: ImageView = itemView.findViewById(R.id.img_item)
}
