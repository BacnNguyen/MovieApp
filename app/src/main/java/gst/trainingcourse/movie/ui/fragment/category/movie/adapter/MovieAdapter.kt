package gst.trainingcourse.movie.ui.fragment.category.movie.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import gst.trainingcourse.movie.BuildConfig
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.databinding.ItemMovie2Binding
import gst.trainingcourse.movie.ui.adapter.BaseAdapter

class MovieAdapter (private val onItemClick: (MovieResponse.Movie, Int) -> Unit) :
    BaseAdapter<MovieResponse.Movie, ItemMovie2Binding>(R.layout.item_movie2) {

    private val requestOptions by lazy {
        RequestOptions().apply {
            fitCenter()
        }
    }

    override fun needRebind(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            root.setOnClickListener {
                onItemClick.invoke(getItem(position), position)
            }
        }
    }

    override fun notNeedRebind(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(root.context)
                .load("${BuildConfig.BASE_IMAGE_URL}${getItem(position).posterPath}")
                .apply(requestOptions)
                .into(imageViewPoster)
            textViewName.text=getItem(position).title
        }

    }
}