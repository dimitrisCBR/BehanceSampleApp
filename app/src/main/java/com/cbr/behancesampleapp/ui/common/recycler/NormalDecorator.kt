package com.cbr.behancesampleapp.ui.common.recycler

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

import com.cbr.behancesampleapp.R

/** Created by Dimitrios on 8/26/2017. */
class NormalDecorator(
    context: Context) : RecyclerView.ItemDecoration() {
    private val padding: Int = context.resources.getDimension(R.dimen.card_standard_padding).toInt()

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemPosition = parent.getChildAdapterPosition(view)

        val isTop = itemPosition == 0
        val isBottom = itemPosition == parent.adapter?.itemCount?.minus(1) ?: false

        outRect.top = if (isTop) padding / 4 else padding / 2
        outRect.bottom = if (isBottom) padding / 4 else padding / 2
    }
}
