import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.homePage.model.Movie
import com.example.movie.homePage.view.adapters.HorizontalAdapter
import com.example.movie.homePage.view.adapters.viewHolders.HorizontalViewHolder

class VerticalAdapter(private var movies: List<Movie> = arrayListOf()) :
    RecyclerView.Adapter<HorizontalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_horizantal, parent, false)
        return HorizontalViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        // get horizontal recycler view and set adapter, layout manager and pass the data (list of movies to it)
        val movie = movies[position]
        holder.let {
            it.txvTitle.text = movie.movieTitle
            // bind items to each recycler-view
            it.recItems.let { recyclerView ->
                recyclerView.layoutManager =
                    LinearLayoutManager(recyclerView.context, RecyclerView.HORIZONTAL, false)
                recyclerView.adapter = HorizontalAdapter(movies)
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
