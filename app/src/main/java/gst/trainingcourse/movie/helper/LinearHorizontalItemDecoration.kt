package gst.trainingcourse.movie.helper

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class LinearHorizontalItemDecoration(private val distance: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        if (itemPosition != 0) {
            outRect.set(distance, 0, 0, 0)
        }
    }
}