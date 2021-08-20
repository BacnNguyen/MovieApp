package gst.trainingcourse.movie.ui.fragment.home.adapter

import android.annotation.SuppressLint
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import gst.trainingcourse.movie.BuildConfig
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.databinding.ItemMovieBinding
import gst.trainingcourse.movie.ui.adapter.BaseAdapter

@SuppressLint("CheckResult")
class MovieAdapter(private val onItemClick: (MovieResponse.Movie, Int) -> Unit) :
    BaseAdapter<MovieResponse.Movie, ItemMovieBinding>(R.layout.item_movie) {

    private val requestOptions by lazy {
        RequestOptions().apply {
            transform(CenterCrop())
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
                .into(imageView)
        }
    }
}