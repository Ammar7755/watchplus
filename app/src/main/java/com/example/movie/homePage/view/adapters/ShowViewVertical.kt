import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R

class ShowViewVertical(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txvTitle: TextView = itemView.findViewById(R.id.txv_title)

}
