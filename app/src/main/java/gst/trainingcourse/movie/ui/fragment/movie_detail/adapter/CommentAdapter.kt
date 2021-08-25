package gst.trainingcourse.movie.ui.fragment.movie_detail.adapter

import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.data.model.Comment
import gst.trainingcourse.movie.databinding.ItemCommentBinding
import gst.trainingcourse.movie.ui.adapter.BaseAdapter

class CommentAdapter : BaseAdapter<Comment, ItemCommentBinding>(
    R.layout.item_comment
) {

    override fun needRebind(holder: ViewHolder, position: Int) {

    }

    override fun notNeedRebind(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            textViewUser.text = getItem(position).user
            textViewComment.text = getItem(position).message
        }
    }
}