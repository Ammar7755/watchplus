package com.example.movie.homePage.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.homePage.model.Movie
import com.example.movie.homePage.view.adapters.viewHolders.ProductItemViewHolder

class HorizontalAdapter(private val items: List<Movie> = arrayListOf()) :
    RecyclerView.Adapter<ProductItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val item = items[position]
        holder.txvItem.text = item.movieTitle
        // load image using glide
        val imageUrl = "https://image.tmdb.org/t/p/w500/" + item.posterPath
        Glide.with(holder.imgItem)
            .load(imageUrl) // image url
            .placeholder(R.drawable.background)
            .error(R.drawable.background)
            .override(200, 300) // resizing
            .centerCrop()
            .into(holder.imgItem)

        // "https://api.themoviedb.org/3/movie/{image_id}/images/{poster_path}
    }
}