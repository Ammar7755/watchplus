import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.homePage.model.Movie
import com.example.movie.homePage.view.adapters.ItemsAdapter
import com.example.movie.homePage.view.adapters.viewHolders.CategoryViewHolder


class CategoriesAdapter(private var movies: List<Movie> = arrayListOf()) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        // get horizontal recycler view and set adapter, layout manager and pass the data (list of movies to it)
        val movie = movies[position]
        holder.let {
            it.txvTitle.text = movie.movieTitle
            // bind items to each recycler-view
            it.recItems.let { recyclerView ->
                recyclerView.layoutManager =
                    LinearLayoutManager(recyclerView.context, RecyclerView.HORIZONTAL, false)
                recyclerView.adapter = ItemsAdapter(movies)
            }
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateRecyclerViewData(movies: List<Movie>?) {
        movies?.let {
            this.movies = movies
            notifyDataSetChanged()
        }
    }
}
