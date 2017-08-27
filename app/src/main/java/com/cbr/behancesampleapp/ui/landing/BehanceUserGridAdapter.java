package com.cbr.behancesampleapp.ui.landing;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cbr.behancesampleapp.model.BehanceUser;
import com.cbr.behancesampleapp.ui.components.LoadingViewHolder;
import com.cbr.behancesampleapp.ui.components.PagingAdapter;
import com.cbr.behancesampleapp.ui.components.PagingListItem;

/**
 * Created by Dimitrios on 8/26/2017.
 */

public class BehanceUserGridAdapter extends PagingAdapter<BehanceUser, RecyclerView.ViewHolder> {

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (viewType == PagingListItem.Type.LOADING.ordinal()) {
			return LoadingViewHolder.newInstance(parent);
		}
		return BehanceUserGridViewHolder.newInstance(parent);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
		//ignore other types, unless you want to mess with the Loader.
		if (holder instanceof BehanceUserGridViewHolder) {
			((BehanceUserGridViewHolder) holder).onBind(getData().get(position).getItem(), position);
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					getInteractor().onListItemClicked(getData().get(position).getItem(), position);
				}
			});
		}
	}

}
