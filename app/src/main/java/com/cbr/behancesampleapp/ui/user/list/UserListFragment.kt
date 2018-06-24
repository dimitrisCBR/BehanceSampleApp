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
import com.cbr.behancesampleapp.ui.common.MvpView
import com.cbr.behancesampleapp.ui.common.mvp.BaseMvpFragment
import com.cbr.behancesampleapp.ui.common.recycler.PagingAdapter
import com.cbr.behancesampleapp.ui.landing.recycler.BehanceUserGridAdapter
import com.cbr.behancesampleapp.ui.landing.recycler.BehanceUserItemDecorator
import com.cbr.behancesampleapp.ui.user.DaggerUserComponent
import com.cbr.behancesampleapp.ui.user.UserModule
import com.cbr.behancesampleapp.ui.user.details.UserDetailsActivity
import com.cbr.behancesampleapp.util.UiUtils
import kotlinx.android.synthetic.main.include_appbarlayout.*
import kotlinx.android.synthetic.main.include_layout_recyclerview.*
import javax.inject.Inject

interface UserListView : MvpView {

    fun onUsersFetched(behanceUser: List<BehanceUser>)

    fun showError()
}

class UserListFragment : BaseMvpFragment(), UserListView, PagingAdapter.Interactor<BehanceUser> {

    @Inject
    lateinit var presenter: UserListPresenter

    var gridAdapter = BehanceUserGridAdapter(this)

    private val columnCount: Int
        get() {
            val itemWidth = (resources.getDimension(R.dimen.card_behace_user_item) + resources.getDimension(R.dimen.card_standard_padding)).toInt()
            return UiUtils.getScreenWidth(context) / itemWidth
        }

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

    private fun bindViews() {
        toolbar.setTitle(R.string.dictionary_browse)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        recyclerView.apply {
            val gridLayoutManager = GridLayoutManager(context, columnCount)
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == gridAdapter.itemCount - 1) columnCount else 1
                }
            }
            layoutManager = gridLayoutManager
            addItemDecoration(BehanceUserItemDecorator(context, columnCount))
            adapter = gridAdapter
        }
        swipeRefreshLayout.apply {
            setColorSchemeColors(
                ContextCompat.getColor(context, R.color.colorAccent),
                ContextCompat.getColor(context, R.color.colorPrimaryDark))
            setOnRefreshListener { presenter.refresh() }
        }
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

    override fun requestMoreData() {
        presenter.requestBehanceUsers()
    }

    override fun onListItemClicked(item: BehanceUser, position: Int) {
        startActivity(UserDetailsActivity.newIntent(context, item.id))
    }

    override fun onUsersFetched(behanceUser: List<BehanceUser>) {
        gridAdapter.onDataLoaded(behanceUser)
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}