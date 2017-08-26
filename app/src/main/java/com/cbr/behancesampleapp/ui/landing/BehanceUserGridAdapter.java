package com.cbr.behancesampleapp.ui.landing;

import android.view.ViewGroup;

import com.cbr.behancesampleapp.model.BehanceUser;
import com.cbr.behancesampleapp.ui.components.PagingAdapter;

/**
 * Created by Dimitrios on 8/26/2017.
 */

public class BehanceUserGridAdapter extends PagingAdapter<BehanceUser, BehanceUserGridViewHolder> {

	@Override
	public BehanceUserGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return BehanceUserGridViewHolder.newInstance(parent, viewType);
	}

	@Override
	public void onBindViewHolder(BehanceUserGridViewHolder holder, int position) {
		holder.onBind(getData().get(position), position);
	}

}
