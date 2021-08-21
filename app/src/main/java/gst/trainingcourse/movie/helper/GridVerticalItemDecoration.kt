package gst.trainingcourse.movie.helper

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class GridVerticalItemDecoration(private val distance: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.set(distance, distance, distance, distance)
    }
}