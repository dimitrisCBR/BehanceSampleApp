package com.cbr.behancesampleapp.ui.landing;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbr.behancesampleapp.R;
import com.cbr.behancesampleapp.model.BehanceUser;
import com.cbr.behancesampleapp.util.UiUtils;
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
	@BindView(R.id.card_behance_user_extra_divider)
	View mExtraDivider;

	public static BehanceUserGridViewHolder newInstance(ViewGroup parent) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_behance_user_item, parent, false);
		return new BehanceUserGridViewHolder(view);
	}

	public BehanceUserGridViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	public void onBind(BehanceUser user, int position) {
		UiUtils.loadImageInto(mImage, user.getImages().getLargeUrl());

		mTitle.setText(user.getDisplayName());
		mSubtitle.setText(user.getCountry());
		mExtraDivider.setVisibility(TextUtils.isEmpty(user.getOccupation()) ? View.GONE : View.VISIBLE);
		mExtraInfo.setVisibility(TextUtils.isEmpty(user.getOccupation()) ? View.GONE : View.VISIBLE);
		mExtraInfo.setText(user.getOccupation());
	}
}