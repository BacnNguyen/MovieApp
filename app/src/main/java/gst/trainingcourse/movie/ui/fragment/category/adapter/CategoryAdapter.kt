package gst.trainingcourse.movie.ui.fragment.category.adapter

import android.annotation.SuppressLint
import android.content.res.Resources
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import gst.trainingcourse.movie.BuildConfig
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.data.model.Category
import gst.trainingcourse.movie.databinding.ItemCategoryBinding
import gst.trainingcourse.movie.ui.adapter.BaseAdapter

@SuppressLint("CheckResult")
class CategoryAdapter(
    private val resource: Resources,
    private val onItemClick: (Category, Int) -> Unit
) :
    BaseAdapter<Category, ItemCategoryBinding>(R.layout.item_category) {

    private val requestOptions by lazy {
        RequestOptions().apply {
            transform(CenterCrop(), RoundedCorners(resource.getDimensionPixelOffset(R.dimen.dp_10)))
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
                .load("${BuildConfig.BASE_IMAGE_URL}${getItem(position).previewPath}")
                .apply(requestOptions)
                .into(imageView)

            textView.text = getItem(position).name
        }
    }
}