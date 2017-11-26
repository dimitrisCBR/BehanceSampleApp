package com.cbr.behancesampleapp.ui.userdetails;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbr.behancesampleapp.R;
import com.cbr.behancesampleapp.domain.model.BehanceUser;
import com.cbr.behancesampleapp.ui.common.mvp.BaseMvpActivity;
import com.cbr.behancesampleapp.ui.userdetails.mvp.UserDetailsContract;
import com.cbr.behancesampleapp.util.BeTextUtils;
import com.cbr.behancesampleapp.util.UiUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/** Created by dimitrios on 24/08/2017.*/
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
	@BindView(R.id.header_userdetails_occuation)
	TextView mOccupationTextView;
	@BindView(R.id.header_userdetails_date)
	TextView mDateJoinedTextView;
	@BindView(R.id.header_userdetails_fields)
	TextView mFieldsTextView;
	@BindView(R.id.header_userdetails_appreciations)
	TextView mAppreciationsTextView;
	@BindView(R.id.header_userdetails_followers)
	TextView mFollowersTextView;
	@BindView(R.id.header_userdetails_views)
	TextView mViewsTextView;

	@BindView(R.id.activity_user_details_collapsing)
	CollapsingToolbarLayout mCollapsingToolbarLayout;
	@BindView(R.id.activity_user_details_toolbar)
	Toolbar mToolbar;

	private Target mProfilePictureLoadingCallback = new Target() {
		@Override
		public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
			Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
				@Override
				public void onGenerated(Palette palette) {
					loadHeaderViews(bitmap, palette);
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
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			onBackPressed();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();
		getPresenter().fetchUserById(getUserIdFromIntent());
	}

	public long getUserIdFromIntent() {
		return getIntent().getLongExtra(EXTRA_USER_ID, 0);
	}

	@Override
	public void onUserFetched(BehanceUser user) {
		if (getSupportActionBar() == null) {
			mToolbar.setTitle(user.getDisplayName());
			setSupportActionBar(mToolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		UiUtils.loadImageInto(mProfileImageView, user.getImages().getLargeUrl());
		UiUtils.loadImageInto(this, mProfilePictureLoadingCallback, user.getImages().getLargeUrl());
		mOccupationTextView.setText(user.getOccupation());
		//very ugly hack to manage unix timestmap. I feel even worse creating a Util method for a single point of usage.
		mDateJoinedTextView.setText(DateUtils.formatDateTime(this, user.getCreatedOn() * 1000, DateUtils.FORMAT_ABBREV_RELATIVE));
		mFieldsTextView.setText(BeTextUtils.formatUserFields(user.getFields()));

		//Using drawables instead of setting them on XML because of Vector drawables
		NumberFormat format = NumberFormat.getInstance(Locale.getDefault());

		mAppreciationsTextView.setText(format.format(user.getStats().getAppreciations()));
		Drawable medalDrawable = ContextCompat.getDrawable(this, R.drawable.ic_medal);
		mAppreciationsTextView.setCompoundDrawablesWithIntrinsicBounds(medalDrawable, null, null, null);

		mFollowersTextView.setText(format.format(user.getStats().getFollowers()));
		Drawable followerDrawable = ContextCompat.getDrawable(this, R.drawable.ic_follower);
		mFollowersTextView.setCompoundDrawablesWithIntrinsicBounds(followerDrawable, null, null, null);

		mViewsTextView.setText(format.format(user.getStats().getViews()));
		Drawable viewsDrawable = ContextCompat.getDrawable(this, R.drawable.ic_eye);
		mViewsTextView.setCompoundDrawablesWithIntrinsicBounds(viewsDrawable, null, null, null);
	}

	@Override
	public void showError() {
		//TODO
	}

	private void loadHeaderViews(Bitmap bitmap, Palette palette) {
		//Palette specific Swatches may not find a suitable color. Thus we get a default first.
		int defaultPaletteColor = UiUtils.getPaletteColor(palette);
		//animate as fade in to avoid the flash
		final int animationTime = getResources().getInteger(android.R.integer.config_longAnimTime);

		mBackgroundImageView.setAlpha(0f);
		//create new bimap to avoid blurring the original one.
		mBackgroundImageView.setImageBitmap(UiUtils.blurBitmap(getBaseContext(), bitmap));
		ObjectAnimator.ofFloat(mBackgroundImageView, "alpha", 0f, 1f)
			.setDuration(animationTime)
			.start();

		mCollapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(defaultPaletteColor));
		mCollapsingToolbarLayout.setStatusBarScrimColor(palette.getLightMutedColor(defaultPaletteColor));
		mCollapsingToolbarLayout.setBackgroundColor(palette.getMutedColor(defaultPaletteColor));

		mOverlayColorView.setAlpha(0f);
		mOverlayColorView.setBackgroundColor(UiUtils.getPaletteColor(palette));
		ObjectAnimator.ofFloat(mOverlayColorView, "alpha", 0f, 0.33f)
			.setDuration(animationTime * 2)
			.start();
	}
}
