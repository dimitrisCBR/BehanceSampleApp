package com.cbr.behancesampleapp.ui.common.components;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cbr.behancesampleapp.R;

/** Created by Dimitrios on 8/27/2017. */
public class LoadingViewHolder extends RecyclerView.ViewHolder {
    
    private final static int LAYOUT = R.layout.viewholder_loading;
    
    public LoadingViewHolder(View itemView) {
        super(itemView);
    }
    
    public static LoadingViewHolder newInstance(ViewGroup parent) {
        return new LoadingViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(LAYOUT, parent, false)
        );
    }
}
