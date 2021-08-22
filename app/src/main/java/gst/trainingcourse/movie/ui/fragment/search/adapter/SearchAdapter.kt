package gst.trainingcourse.movie.ui.fragment.search.adapter

import android.annotation.SuppressLint
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import gst.trainingcourse.movie.BuildConfig
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.data.model.SearchResponse
import gst.trainingcourse.movie.databinding.ItemSearchBinding
import gst.trainingcourse.movie.ui.adapter.BaseAdapter

@SuppressLint("CheckResult")
class SearchAdapter(private val onItemClick: (SearchResponse.Result, Int) -> Unit) :
    BaseAdapter<SearchResponse.Result, ItemSearchBinding>(R.layout.item_search) {

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
            val item = getItem(position)

            val imagePath = when (item.mediaType) {
                "person" -> item.profilePath
                else -> item.posterPath
            }
            Glide.with(root.context)
                .load("${BuildConfig.BASE_IMAGE_URL}$imagePath")
                .apply(requestOptions)
                .into(imagePoster)
            when (item.mediaType) {
                "tv" -> {
                    textViewName.text = item.name
                    textViewType.text = "TV Show"
                }
                "movie" -> {
                    textViewName.text = item.title
                    textViewType.text = "Movie"
                }
                else -> {
                    textViewName.text = item.name
                    textViewType.text = "Person"
                }
            }
        }
    }
}