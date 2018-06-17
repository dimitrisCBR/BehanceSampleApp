package com.cbr.behancesampleapp.ui.landing.recycler

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

import com.cbr.behancesampleapp.R

/** Created by Dimitrios on 8/26/2017. */
internal class BehanceUserItemDecorator(context: Context, private val mColumnCount: Int) : RecyclerView.ItemDecoration() {
    private val mColumnPadding: Int = context.resources.getDimension(R.dimen.card_standard_padding).toInt()
    
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        
        val itemPosition = parent.getChildAdapterPosition(view)
        
        val isTop = itemPosition < mColumnCount
        val isStart = itemPosition % mColumnCount == 0
        val isEnd = itemPosition % mColumnCount == mColumnCount - 1
        val isBottom = itemPosition >= parent.adapter?.itemCount?.minus(mColumnCount) ?: 0
        
        outRect.top = if (isTop) mColumnPadding else mColumnPadding / 2
        outRect.left = if (isStart) mColumnPadding else mColumnPadding / 2
        outRect.right = if (isEnd) mColumnPadding else mColumnPadding / 2
        outRect.bottom = if (isBottom) mColumnPadding else mColumnPadding / 2
    }
}
