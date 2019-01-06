package com.cbr.behance.common.recycler

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cbr.behance.R


class GridDecorator(
        context: Context,
        private val columnCount: Int) : RecyclerView.ItemDecoration() {
    private val padding: Int = context.resources.getDimension(R.dimen.card_standard_padding).toInt()

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemPosition = parent.getChildAdapterPosition(view)

        val isTop = itemPosition < columnCount
        val isStart = itemPosition % columnCount == 0
        val isEnd = itemPosition % columnCount == columnCount - 1
        val isBottom = itemPosition >= parent.adapter?.itemCount?.minus(columnCount) ?: 0

        outRect.top = if (isTop) padding else padding / 2
        outRect.left = if (isStart) padding else padding / 2
        outRect.right = if (isEnd) padding else padding / 2
        outRect.bottom = if (isBottom) padding else padding / 2
    }
}
