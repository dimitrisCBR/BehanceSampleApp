package com.cbr.behancesampleapp.ui.landing;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cbr.behancesampleapp.model.BehanceUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitrios on 8/26/2017.
 */

public class BehanceUserGridAdapter extends RecyclerView.Adapter<BehanceUserGridViewHolder> {

	private ArrayList<BehanceUser> mBehanceUsers;

	public BehanceUserGridAdapter() {
		this.mBehanceUsers = new ArrayList<>();
	}

	@Override
	public BehanceUserGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return BehanceUserGridViewHolder.newInstance(parent, viewType);
	}

	@Override
	public void onBindViewHolder(BehanceUserGridViewHolder holder, int position) {
		holder.onBind(mBehanceUsers.get(position), position);
	}

	@Override
	public int getItemCount() {
		return mBehanceUsers != null ? mBehanceUsers.size() : 0;
	}

	public void updateUsers(List<BehanceUser> behanceUsers, boolean clearPrevious) {
		if (clearPrevious) {
			int previousCount = getItemCount();
			mBehanceUsers.clear();
			if (previousCount > 0) {
				notifyItemRangeRemoved(0, previousCount);
			}
		}
		if (behanceUsers != null && !behanceUsers.isEmpty()) {
			int currentItems = getItemCount();
			mBehanceUsers.addAll(behanceUsers);
			notifyItemRangeInserted(currentItems, behanceUsers.size());
		}
	}
}
