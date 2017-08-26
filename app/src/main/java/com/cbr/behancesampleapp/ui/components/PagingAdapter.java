package com.cbr.behancesampleapp.ui.components;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Dimitrios on 8/26/2017.
 */

public abstract class PagingAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

	private ArrayList<T> mData = new ArrayList<>();

	private final static int PAGING_THREESHOLD = 5;

	private Interactor mInteractor;

	private boolean mIsLoading;
	private boolean mIsFinished;

	public void setInteractor(Interactor interactor) {
		this.mInteractor = interactor;
	}

	@Override
	public int getItemCount() {
		return mData != null ? mData.size() : 0;
	}

	public ArrayList<T> getData() {
		return this.mData;
	}

	@Override
	public void onBindViewHolder(V holder, int position, List<Object> payloads) {
		super.onBindViewHolder(holder, position, payloads);
		if (mInteractor != null && position > getItemCount() - PAGING_THREESHOLD && !(mIsLoading || mIsFinished)) {
			mInteractor.requestMoreData();
		}
	}

	public void onDataLoaded(Collection<T> data, boolean clearPrevious) {
		mIsLoading = false;
		if (clearPrevious) {
			int previousCount = getItemCount();
			getData().clear();
			if (previousCount > 0) {
				notifyItemRangeRemoved(0, previousCount);
			}
		}
		if (data != null && !data.isEmpty()) {
			int currentItems = getItemCount();
			getData().addAll(data);
			notifyItemRangeInserted(currentItems, data.size());
		}
	}

	public interface Interactor {

		void requestMoreData();

	}
}
