package gst.trainingcourse.movie.helper

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class LinearVerticalItemDecoration(private val distance: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        if (itemPosition > 0) {
            outRect.set(0, distance, 0, 0)
        }
    }
}