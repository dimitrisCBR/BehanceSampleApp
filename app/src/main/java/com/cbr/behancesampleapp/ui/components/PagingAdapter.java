package com.cbr.behancesampleapp.ui.components;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Dimitrios on 8/26/2017.
 */

public abstract class PagingAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

	private ArrayList<PagingListItem<T>> mData = new ArrayList<>();

	private final static int PAGING_THREESHOLD = 7;

	private Interactor mInteractor;
	private PagingListItem<T> mLoadingSpinner = new PagingListItem<>(null, PagingListItem.Type.LOADING);

	private boolean mIsLoading;
	private boolean mIsFinished;

	public void setInteractor(Interactor interactor) {
		this.mInteractor = interactor;
	}

	@Override
	public int getItemCount() {
		return mData.size() + 1;
	}

	@Override
	public int getItemViewType(int position) {
		return position == mData.size() ? PagingListItem.Type.LOADING.ordinal() : mData.get(position).getType().ordinal();
	}

	public ArrayList<PagingListItem<T>> getData() {
		return this.mData;
	}

	@Override
	public void onBindViewHolder(V holder, int position, List<Object> payloads) {
		super.onBindViewHolder(holder, position, payloads);
		if (mInteractor != null && position > mData.size() - PAGING_THREESHOLD && !(mIsLoading || mIsFinished)) {
			startLoading();
			mInteractor.requestMoreData();
		}
	}

	public void onDataLoaded(Collection<T> data, boolean clearPrevious) {
		stopLoading();
		if (clearPrevious) {
			clearData();
		}
		addData(data);
	}

	private void addData(Collection<T> data) {
		if (data != null && !data.isEmpty()) {
			int currentItems = mData.size();
			int newItemsCounter = 0;
			for (T item : data) {
				getData().add(currentItems + newItemsCounter, new PagingListItem<T>(item, PagingListItem.Type.DATA));
				newItemsCounter++;
			}
			notifyItemRemoved(currentItems);
			notifyItemRangeInserted(currentItems, data.size());
		}
	}

	public void clearData() {
		int previousCount = mData.size();
		getData().clear();
		if (previousCount > 0) {
			notifyItemRangeRemoved(0, previousCount);
		}
	}

	public void startLoading() {
		mIsLoading = true;
	}

	public void stopLoading() {
		mIsLoading = false;
	}

	public void onAllItemsLoaded() {
		mIsFinished = true;
	}

	public interface Interactor {

		void requestMoreData();

	}

}
