package gst.trainingcourse.movie.ui.fragment.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.databinding.ItemCommentBinding
import gst.trainingcourse.movie.ui.adapter.BaseAdapter
import gst.trainingcourse.movie.ui.fragment.detail.test_model.Comment
import java.util.zip.Inflater

class MovieCommentAdapter :BaseAdapter<Comment,ItemCommentBinding>(
    R.layout.item_comment){


    override fun needRebind(holder: ViewHolder, position: Int) {

    }

    override fun notNeedRebind(holder: ViewHolder, position: Int) {

    }
}