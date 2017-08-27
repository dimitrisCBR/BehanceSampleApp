package com.cbr.behancesampleapp.ui.userdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cbr.behancesampleapp.R;
import com.cbr.behancesampleapp.mvp.BaseMvpActivity;
import com.cbr.behancesampleapp.ui.userdetails.mvp.UserDetailsContract;

/**
 * Created by dimitrios on 24/08/2017.
 */

public class UserDetailsActivity extends BaseMvpActivity<UserDetailsContract.Presenter> implements UserDetailsContract.View {

	private static final String EXTRA_USER_ID = UserDetailsActivity.class.getCanonicalName() + "EXTRA_USER_ID";

	public static Intent newIntent(Context context, long userId) {
		Intent intent = new Intent(context, UserDetailsActivity.class);
		intent.putExtra(EXTRA_USER_ID, userId);
		return intent;
	}

	@Override
	public UserDetailsContract.Presenter createPresenter() {
		return new UserDetailsPresenter();
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_details);
	}
}
