package com.cbr.behancesampleapp.ui.landing;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbr.behancesampleapp.R;
import com.cbr.behancesampleapp.model.BehanceUser;
import com.squareup.picasso.Picasso;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dimitrios on 8/26/2017.
 */

public class BehanceUserGridViewHolder extends RecyclerView.ViewHolder {

	@BindDimen(R.dimen.card_behace_user_item)
	int mImageDefaultHeight;

	@BindView(R.id.card_behance_user_image)
	ImageView mImage;

	@BindView(R.id.card_behance_user_title)
	TextView mTitle;
	@BindView(R.id.card_behance_user_subtitle)
	TextView mSubtitle;
	@BindView(R.id.card_behance_user_extra_info)
	TextView mExtraInfo;

	public static BehanceUserGridViewHolder newInstance(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_behance_user_item, parent, false);
		return new BehanceUserGridViewHolder(view);
	}

	public BehanceUserGridViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	public void onBind(BehanceUser user, int position) {
		Picasso.with(itemView.getContext())
			.load(user.getImages().getLargeUrl())
			.into(mImage);

		mTitle.setText(user.getUsername());
		mSubtitle.setText(user.getOccupation());
		mExtraInfo.setText(user.getCountry());
	}
}