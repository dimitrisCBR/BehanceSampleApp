package com.cbr.behancesampleapp.ui.userdetails;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.graphics.Palette;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbr.behancesampleapp.R;
import com.cbr.behancesampleapp.model.BehanceUser;
import com.cbr.behancesampleapp.mvp.BaseMvpActivity;
import com.cbr.behancesampleapp.network.BehanceRepository;
import com.cbr.behancesampleapp.ui.userdetails.mvp.UserDetailsContract;
import com.cbr.behancesampleapp.util.UiUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * Created by dimitrios on 24/08/2017.
 */

public class UserDetailsActivity extends BaseMvpActivity<UserDetailsContract.Presenter> implements UserDetailsContract.View {

	private static final String EXTRA_USER_ID = UserDetailsActivity.class.getCanonicalName() + "EXTRA_USER_ID";

	@BindView(R.id.header_userdetails_root)
	View mHeaderBackground;
	@BindView(R.id.header_userdetails_overlay)
	View mOverlayColorView;
	@BindView(R.id.header_userdetails_background)
	ImageView mBackgroundImageView;
	@BindView(R.id.header_userdetails_image)
	RoundedImageView mProfileImageView;
	@BindView(R.id.header_userdetails_title)
	TextView mNameTextView;
	@BindView(R.id.header_userdetails_subtitle)
	TextView mSubTitleTextView;
	@BindView(R.id.header_userdetails_extra)
	TextView mExtraInfoTextView;

	@BindView(R.id.activity_user_details_collapsing)
	CollapsingToolbarLayout mCollapsingToolbarLayout;

	@Inject
	BehanceRepository mBehanceRepository;

	private Target mPicassoLoadingCallback = new Target() {
		@Override
		public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {

			Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
				@Override
				public void onGenerated(Palette palette) {
					//animate as fade in to avoid the flash
					int animationTime = getResources().getInteger(android.R.integer.config_longAnimTime);

					int paletteColor = UiUtils.getPaletteColor(palette);

					mCollapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(paletteColor));
					mCollapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(paletteColor));
					mCollapsingToolbarLayout.setBackgroundColor(palette.getMutedColor(paletteColor));

					mOverlayColorView.setAlpha(0f);
					ObjectAnimator.ofFloat(mOverlayColorView, "alpha", 0f, 0.66f)
						.setDuration(animationTime * 2)
						.start();
					mOverlayColorView.setBackgroundColor(UiUtils.getPaletteColor(palette));

					mBackgroundImageView.setAlpha(0f);
					ObjectAnimator.ofFloat(mBackgroundImageView, "alpha", 0f, 1f)
						.setDuration(animationTime)
						.start();
					mBackgroundImageView.setImageBitmap(bitmap);
				}
			});
		}

		@Override
		public void onBitmapFailed(Drawable errorDrawable) {
			//TODO
		}

		@Override
		public void onPrepareLoad(Drawable placeHolderDrawable) {
			//no-op
		}
	};


	public static Intent newIntent(Context context, long userId) {
		Intent intent = new Intent(context, UserDetailsActivity.class);
		intent.putExtra(EXTRA_USER_ID, userId);
		return intent;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		AndroidInjection.inject(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_details);
		ButterKnife.bind(this);
	}

	@Override
	public UserDetailsContract.Presenter createPresenter() {
		return new UserDetailsPresenter(mBehanceRepository, getUserIdFromIntent());
	}

	@Override
	protected void onResume() {
		super.onResume();
		getPresenter().refresh();
	}

	public long getUserIdFromIntent() {
		return getIntent().getLongExtra(EXTRA_USER_ID, 0);
	}

	@Override
	public void onUserFetched(BehanceUser user) {
		UiUtils.loadImageInto(mProfileImageView, user.getImages().getLargeUrl());
		UiUtils.loadImageInto(this, mPicassoLoadingCallback, user.getImages().getLargeUrl());
		mNameTextView.setText(user.getDisplayName());
		mSubTitleTextView.setText(user.getOccupation());
		mExtraInfoTextView.setText(DateUtils.formatDateTime(this, user.getCreatedOn(), DateUtils.FORMAT_ABBREV_RELATIVE));
	}

	@Override
	public void showError() {
		//TODO
	}
}
