package com.cbr.behancesampleapp.ui.common.components;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Dimitrios on 8/27/2017.
 */

public class PagingListItem<T> {

	private Type type;
	private T item;

	public PagingListItem(@Nullable T item, @NonNull Type type) {
		this.item = item;
		this.type = type;
	}

	@Nullable
	public T getItem() {
		return item;
	}

	public Type getType() {
		return type;
	}

	public enum Type {
		LOADING,
		DATA,
		HEADER
	}
}
