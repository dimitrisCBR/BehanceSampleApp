package com.cbr.behancesampleapp.ui.landing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cbr.behancesampleapp.R;
import com.cbr.behancesampleapp.model.BehanceUser;
import com.cbr.behancesampleapp.mvp.BaseMvpActivity;
import com.cbr.behancesampleapp.network.BehanceRepository;
import com.cbr.behancesampleapp.ui.landing.mvp.LandingActivityContract;
import com.cbr.behancesampleapp.util.UiUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class LandingActivity extends BaseMvpActivity<LandingActivityContract.Presenter> implements LandingActivityContract.View {

	private BehanceUserGridAdapter mGridAdapter;

	@BindView(R.id.activity_landing_recycler)
	RecyclerView mRecyclerView;
	@BindView(R.id.activity_landing_swiperefresh)
	SwipeRefreshLayout mSwipeRefreshLayout;

	@Inject
	BehanceRepository mBehanceRepository;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		AndroidInjection.inject(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landing);
		bindViews();
	}

	private void bindViews() {
		ButterKnife.bind(this);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		mGridAdapter = new BehanceUserGridAdapter();

		mRecyclerView.setLayoutManager(new GridLayoutManager(this, getColumnCount()));
		mRecyclerView.addItemDecoration(new BehanceUserItemDecorator(this, getColumnCount()));
		mRecyclerView.setAdapter(mGridAdapter);
		mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				getPresenter().requestBehanceUsers();
			}
		});

		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getPresenter().requestBehanceUsers();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		getPresenter().requestBehanceUsers();
	}

	private int getColumnCount() {
		int screenWidth = UiUtils.getScreenWidth(this);
		int itemWidth = (int) (getResources().getDimension(R.dimen.card_behace_user_item) + getResources().getDimension(R.dimen.card_standard_padding));
		return screenWidth / itemWidth;
	}

	@Override
	public LandingActivityContract.Presenter createPresenter() {
		return new LandingActivityPresenter(mBehanceRepository);
	}

	@Override
	public void onUsersFetched(List<BehanceUser> behanceUser) {
		mSwipeRefreshLayout.setRefreshing(false);
		mGridAdapter.updateUsers(behanceUser, true);
	}

	@Override
	public void showError() {
		mSwipeRefreshLayout.setRefreshing(false);
	}
}
