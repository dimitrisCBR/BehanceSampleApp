package com.cbr.behancesampleapp.ui.landing

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import butterknife.ButterKnife
import com.cbr.behancesampleapp.R
import com.cbr.behancesampleapp.domain.model.BehanceUser
import com.cbr.behancesampleapp.ui.common.mvp.BaseMvpActivity
import com.cbr.behancesampleapp.ui.common.recycler.PagingAdapter
import com.cbr.behancesampleapp.ui.landing.dagger.DaggerLandingComponent
import com.cbr.behancesampleapp.ui.landing.dagger.LandingActivityModule
import com.cbr.behancesampleapp.ui.landing.mvp.LandingActivityContract
import com.cbr.behancesampleapp.ui.userdetails.UserDetailsActivity
import com.cbr.behancesampleapp.util.UiUtils
import kotlinx.android.synthetic.main.activity_landing.*
import javax.inject.Inject

class LandingActivity : BaseMvpActivity(), LandingActivityContract.View,
        PagingAdapter.Interactor<BehanceUser>, NavigationView.OnNavigationItemSelectedListener {
    
    override fun onActivityInject() {
        DaggerLandingComponent.builder()
                .appComponent(appComponent())
                .landingActivityModule(LandingActivityModule(this))
                .build().inject(this)
    }
    
    @Inject
    lateinit var mPresenter: LandingActivityPresenter
    
    var mGridAdapter = BehanceUserGridAdapter(this as PagingAdapter.Interactor<BehanceUser>)
    
    private val columnCount: Int
        get() {
            val screenWidth = UiUtils.getScreenWidth(this)
            val itemWidth = (resources.getDimension(R.dimen.card_behace_user_item) + resources.getDimension(R.dimen.card_standard_padding)).toInt()
            return screenWidth / itemWidth
        }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        bindViews()
    }
    
    private fun bindViews() {
        ButterKnife.bind(this)
        
        landingToolbar.setTitle(R.string.dictionary_browse)
        setSupportActionBar(landingToolbar)
        
        val toggle = ActionBarDrawerToggle(this, landingDrawerLayout, landingToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        landingDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        landingNavigationView.setNavigationItemSelectedListener(this)
        
        val gridLayoutManager = GridLayoutManager(this, columnCount)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            
            override fun getSpanSize(position: Int): Int {
                return if (position == mGridAdapter!!.itemCount - 1) columnCount else 1
            }
        }
        landingRecyclerView.layoutManager = gridLayoutManager
        landingRecyclerView.addItemDecoration(BehanceUserItemDecorator(this, columnCount))
        landingRecyclerView.adapter = mGridAdapter
        
        landingSwipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorPrimaryDark))
        landingSwipeRefresh.setOnRefreshListener { mPresenter.refresh() }
        landingFab.setOnClickListener { landingRecyclerView.smoothScrollToPosition(0) }
    }
    
    override fun onResume() {
        super.onResume()
        mPresenter.requestBehanceUsers()
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_landing, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_landing_filter -> showFilterScreen()
        }
        return super.onOptionsItemSelected(item)
    }
    
    private fun showFilterScreen() {
        //TODO
    }
    
    override fun onUsersFetched(behanceUser: List<BehanceUser>, clearPrevious: Boolean) {
        landingSwipeRefresh.isRefreshing = false
        mGridAdapter.onDataLoaded(behanceUser, clearPrevious)
    }
    
    override fun showError() {
        landingSwipeRefresh.isRefreshing = false
    }
    
    override fun requestMoreData() {
        mPresenter.requestBehanceUsers()
    }
    
    override fun onListItemClicked(item: BehanceUser, position: Int) {
        startActivity(UserDetailsActivity.newIntent(this, item.id))
    }
    
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }
}
