package com.cbr.behancesampleapp.ui.user.list

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cbr.behancesampleapp.R
import com.cbr.behancesampleapp.model.BehanceUser
import com.cbr.behancesampleapp.ui.common.GridFragment
import com.cbr.behancesampleapp.ui.common.MvpView
import com.cbr.behancesampleapp.ui.common.recycler.PagingAdapter
import com.cbr.behancesampleapp.ui.common.recycler.GridDecorator
import com.cbr.behancesampleapp.ui.user.DaggerUserComponent
import com.cbr.behancesampleapp.ui.user.UserModule
import com.cbr.behancesampleapp.ui.user.details.UserDetailsActivity
import kotlinx.android.synthetic.main.include_appbarlayout.*
import kotlinx.android.synthetic.main.include_layout_empty.*
import kotlinx.android.synthetic.main.include_layout_progress.*
import kotlinx.android.synthetic.main.include_layout_recyclerview.*
import javax.inject.Inject

interface UserListView : MvpView {

    fun onUsersFetched(behanceUser: List<BehanceUser>)

    fun showError(errorMsg: String?)

    fun showLoading()

    fun showContent()
}

class UserListFragment : GridFragment(), UserListView, PagingAdapter.Interactor<BehanceUser> {

    @Inject
    lateinit var presenter: UserListPresenter

    var gridAdapter = UserGridAdapter(this)

    override fun onFragmentInject() {
        DaggerUserComponent.builder()
            .appComponent(appComponent())
            .userModule(UserModule())
            .build().inject(this)
        presenter.attachView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
    }

    override fun onStart() {
        super.onStart()
        presenter.onSubscribe()
        presenter.requestBehanceUsers()
    }

    override fun onStop() {
        super.onStop()
        presenter.onUnsubscribe()
    }

    private fun bindViews() {
        toolbar.setTitle(R.string.title_users)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        recyclerView.apply {
            val gridLayoutManager = GridLayoutManager(context, columnCount)
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == gridAdapter.itemCount - 1) columnCount else 1
                }
            }
            layoutManager = gridLayoutManager
            addItemDecoration(GridDecorator(context, columnCount))
            adapter = gridAdapter
        }
        swipeRefreshLayout.apply {
            setColorSchemeColors(
                ContextCompat.getColor(context, R.color.colorAccent),
                ContextCompat.getColor(context, R.color.colorPrimaryDark))
            setOnRefreshListener { presenter.refresh() }
        }
        retryButton.setOnClickListener { requestMoreData() }
        showContent()
    }

    override fun requestMoreData() {
        presenter.requestBehanceUsers()
    }

    override fun onListItemClicked(item: BehanceUser, position: Int) {
        startActivity(UserDetailsActivity.newIntent(context, item.id))
    }

    override fun onUsersFetched(behanceUser: List<BehanceUser>) {
        showContent()
        gridAdapter.onDataLoaded(behanceUser)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        /* Take in account the loading viewholder */
        if (gridAdapter.itemCount <= 1) {
            recyclerView.visibility = View.GONE
        } else {
            recyclerView.visibility = View.VISIBLE
        }
        emptyContainer.visibility = View.GONE
    }

    override fun showContent() {
        swipeRefreshLayout.isRefreshing = false
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        emptyContainer.visibility = View.GONE
    }

    override fun showError(errorMsg: String?) {
        swipeRefreshLayout.isRefreshing = false
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE
        emptyContainer.visibility = View.VISIBLE
        emptyTextView.text = errorMsg ?: getString(R.string.message_empty_content)
    }
}