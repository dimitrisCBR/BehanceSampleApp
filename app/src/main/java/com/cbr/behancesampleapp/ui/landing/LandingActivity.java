package com.cbr.behancesampleapp.ui.landing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cbr.behancesampleapp.R;
import com.cbr.behancesampleapp.model.BehanceUser;
import com.cbr.behancesampleapp.mvp.BaseMvpActivity;
import com.cbr.behancesampleapp.network.BehanceRepository;
import com.cbr.behancesampleapp.ui.components.PagingAdapter;
import com.cbr.behancesampleapp.ui.landing.mvp.LandingActivityContract;
import com.cbr.behancesampleapp.util.UiUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class LandingActivity extends BaseMvpActivity<LandingActivityContract.Presenter> implements LandingActivityContract.View,
	PagingAdapter.Interactor<BehanceUser> {

	private BehanceUserGridAdapter mGridAdapter;

	@BindView(R.id.activity_landing_recycler)
	RecyclerView mRecyclerView;
	@BindView(R.id.activity_landing_swiperefresh)
	SwipeRefreshLayout mSwipeRefreshLayout;
	@BindView(R.id.activity_landing_fab)
	FloatingActionButton mFab;

	@Inject
	BehanceRepository mBehanceRepository;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		AndroidInjection.inject(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landing);
		bindViews();
	}

	@Override
	public LandingActivityContract.Presenter createPresenter() {
		return new LandingActivityPresenter(mBehanceRepository);
	}

	private void bindViews() {
		ButterKnife.bind(this);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		mGridAdapter = new BehanceUserGridAdapter();
		mGridAdapter.setInteractor(this);

		GridLayoutManager gridLayoutManager = new GridLayoutManager(this, getColumnCount());
		gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {
				return position == mGridAdapter.getItemCount() - 1 ? getColumnCount() : 1;
			}
		});
		mRecyclerView.setLayoutManager(gridLayoutManager);
		mRecyclerView.addItemDecoration(new BehanceUserItemDecorator(this, getColumnCount()));
		mRecyclerView.setAdapter(mGridAdapter);

		mSwipeRefreshLayout.setColorSchemeColors(
			ContextCompat.getColor(this,R.color.colorPrimary),
			ContextCompat.getColor(this,R.color.colorPrimaryDark),
			ContextCompat.getColor(this,R.color.colorAccent));
		mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				getPresenter().refresh();
			}
		});
		mFab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mRecyclerView.smoothScrollToPosition(0);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		getPresenter().requestBehanceUsers();
	}

	@Override
	public void onUsersFetched(List<BehanceUser> behanceUser, boolean clearPrevious) {
		mSwipeRefreshLayout.setRefreshing(false);
		mGridAdapter.onDataLoaded(behanceUser, clearPrevious);
	}

	@Override
	public void showError() {
		mSwipeRefreshLayout.setRefreshing(false);
	}

	@Override
	public void requestMoreData() {
		getPresenter().requestBehanceUsers();
	}

	@Override
	public void onListItemClicked(BehanceUser item, int position) {
		//TODO
	}

	private int getColumnCount() {
		int screenWidth = UiUtils.getScreenWidth(this);
		int itemWidth = (int) (getResources().getDimension(R.dimen.card_behace_user_item) + getResources().getDimension(R.dimen.card_standard_padding));
		return screenWidth / itemWidth;
	}

}
