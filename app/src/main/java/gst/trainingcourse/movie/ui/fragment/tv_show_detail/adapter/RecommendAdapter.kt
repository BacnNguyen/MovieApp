package gst.trainingcourse.movie.ui.fragment.tv_show_detail.adapter

import android.annotation.SuppressLint
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import gst.trainingcourse.movie.BuildConfig
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.data.model.TvShowResponse
import gst.trainingcourse.movie.databinding.ItemMovieRecommendBinding
import gst.trainingcourse.movie.ui.adapter.BaseAdapter


@SuppressLint("CheckResult")
class RecommendAdapter(private val onItemClick: (TvShowResponse.TvShow) -> Unit) :
    BaseAdapter<TvShowResponse.TvShow, ItemMovieRecommendBinding>(R.layout.item_movie_recommend) {

    private val requestOptions by lazy {
        RequestOptions().apply {
            transform(CenterCrop())
        }
    }

    override fun needRebind(holder: ViewHolder, position: Int) {
        holder.binding.avtMovieRecommend.setOnClickListener {
            onItemClick.invoke(getItem(position))
        }
    }

    override fun notNeedRebind(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(root.context)
                .load("${BuildConfig.BASE_IMAGE_URL}${getItem(position).posterPath}")
                .apply(requestOptions)
                .into(avtMovieRecommend)

            nameMovieRecommend.text = getItem(position).title
        }
    }

}
