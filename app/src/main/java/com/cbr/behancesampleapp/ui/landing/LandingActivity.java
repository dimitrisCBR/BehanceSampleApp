package com.cbr.behancesampleapp.ui.landing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.cbr.behancesampleapp.R;
import com.cbr.behancesampleapp.domain.model.BehanceUser;
import com.cbr.behancesampleapp.ui.common.mvp.BaseMvpActivity;
import com.cbr.behancesampleapp.domain.network.BehanceRepository;
import com.cbr.behancesampleapp.ui.common.components.PagingAdapter;
import com.cbr.behancesampleapp.ui.landing.mvp.LandingActivityContract;
import com.cbr.behancesampleapp.ui.userdetails.UserDetailsActivity;
import com.cbr.behancesampleapp.util.UiUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class LandingActivity extends BaseMvpActivity<LandingActivityContract.Presenter> implements LandingActivityContract.View,
	PagingAdapter.Interactor<BehanceUser>, NavigationView.OnNavigationItemSelectedListener {

	private BehanceUserGridAdapter mGridAdapter;

	@BindView(R.id.activity_landing_recycler)
	RecyclerView mRecyclerView;
	@BindView(R.id.activity_landing_swiperefresh)
	SwipeRefreshLayout mSwipeRefreshLayout;
	@BindView(R.id.activity_landing_fab)
	FloatingActionButton mFab;
	@BindView(R.id.activity_landing_toolbar)
	Toolbar mToolbar;
	@BindView(R.id.activity_landing_drawerlayout)
	DrawerLayout mDrawerLayout;
	@BindView(R.id.activity_landing_navview)
	NavigationView mNavigationView;

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
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_landing, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_landing_filter:
				showFilterScreen();
				break;
			default:
		}
		return super.onOptionsItemSelected(item);
	}

	private void bindViews() {
		ButterKnife.bind(this);

		mToolbar.setTitle(R.string.dictionary_browse);
		setSupportActionBar(mToolbar);

		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		mDrawerLayout.addDrawerListener(toggle);
		toggle.syncState();
		mNavigationView.setNavigationItemSelectedListener(this);


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
			ContextCompat.getColor(this, R.color.colorAccent),
			ContextCompat.getColor(this, R.color.colorPrimaryDark));
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
		startActivity(UserDetailsActivity.newIntent(this, item.getId()));
	}

	private void showFilterScreen() {
		//TODO
	}

	private int getColumnCount() {
		int screenWidth = UiUtils.getScreenWidth(this);
		int itemWidth = (int) (getResources().getDimension(R.dimen.card_behace_user_item) + getResources().getDimension(R.dimen.card_standard_padding));
		return screenWidth / itemWidth;
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		return false;
	}
}
