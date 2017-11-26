package com.cbr.behancesampleapp.ui.landing;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cbr.behancesampleapp.R;

/** Created by Dimitrios on 8/26/2017.*/
class BehanceUserItemDecorator extends RecyclerView.ItemDecoration {

	private Context mContext;
	private int mColumnCount;
	private int mColumnPadding;

	public BehanceUserItemDecorator(Context context, int columnCount) {
		this.mContext = context;
		this.mColumnCount = columnCount;
		this.mColumnPadding = (int) context.getResources().getDimension(R.dimen.card_standard_padding);
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);

		int itemPosition = parent.getChildAdapterPosition(view);

		boolean isTop = itemPosition < mColumnCount;
		boolean isStart = itemPosition % mColumnCount == 0;
		boolean isEnd = itemPosition % mColumnCount == mColumnCount - 1;
		boolean isBottom = itemPosition >= parent.getAdapter().getItemCount() - mColumnCount;

		outRect.top = isTop ? mColumnPadding : mColumnPadding / 2;
		outRect.left = isStart ? mColumnPadding : mColumnPadding / 2;
		outRect.right = isEnd ? mColumnPadding : mColumnPadding / 2;
		outRect.bottom = isBottom ? mColumnPadding : mColumnPadding / 2;
	}
}
