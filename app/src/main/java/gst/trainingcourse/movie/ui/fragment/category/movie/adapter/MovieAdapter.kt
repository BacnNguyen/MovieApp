package gst.trainingcourse.movie.ui.fragment.category.movie.adapter

import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import gst.trainingcourse.movie.BuildConfig
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.databinding.ItemMovie2Binding
import gst.trainingcourse.movie.ui.adapter.BaseAdapter

class MovieAdapter(private val onItemClick: (MovieResponse.Movie, Int) -> Unit) :
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
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        holder.binding.progressLoading.visibility = View.GONE
                        return false
                    }
                })
                .into(imageViewPoster)
            textViewName.text = getItem(position).title
        }

    }
}