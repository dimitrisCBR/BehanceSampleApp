package com.cbr.behance.commons.recycler.decorator

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import com.futureworkshops.core.R


class PaddedItemDecorator : RecyclerView.ItemDecoration {

    private var mPadding: Int = 0

    constructor(context: Context) {
        this.mPadding = context.resources.getDimension(R.dimen.xsmall).toInt()
    }

    constructor(context: Context, @DimenRes dimen: Int) {
        this.mPadding = context.resources.getDimension(dimen).toInt()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)

        outRect.top = if (position == 0) mPadding else mPadding / 2
        outRect.left = mPadding
        outRect.right = mPadding
        outRect.bottom = mPadding
    }
}
