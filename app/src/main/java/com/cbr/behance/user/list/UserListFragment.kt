package com.cbr.behance.user.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.cbr.base.extension.gone
import com.cbr.base.extension.screenWidth
import com.cbr.base.extension.visible
import com.cbr.behance.BehanceSampleApplication
import com.cbr.behance.R
import com.cbr.behance.commons.recycler.PagingAdapter
import com.cbr.behance.commons.recycler.decorator.GridDecorator
import com.cbr.behance.user.di.DaggerUserComponent
import com.cbr.behance.user.list.recycler.UserGridAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.include_error_layout.*
import kotlinx.android.synthetic.main.include_loading.*
import timber.log.Timber
import javax.inject.Inject


class UserListFragment : Fragment(), PagingAdapter.Callback {

    @Inject
    lateinit var viewModelFactory: UserListViewModelFactory

    private val usersViewModel: UserListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(UserListViewModel::class.java)
    }

    lateinit var gridAdapter: UserGridAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerUserComponent
                .builder()
                .applicationComponent(BehanceSampleApplication.appComponent)
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerview.apply {
            val itemWidth = (resources.getDimension(R.dimen.card_behace_user_item) + resources.getDimension(R.dimen.card_standard_padding)).toInt()
            val columnCount = context.screenWidth() / itemWidth
            val gridLayoutManager = GridLayoutManager(context, columnCount)
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int = gridAdapter.getSpanForPosition(position)
            }
            layoutManager = gridLayoutManager
            addItemDecoration(GridDecorator(context, columnCount))
            gridAdapter = UserGridAdapter(columnCount, this@UserListFragment)
            adapter = gridAdapter
        }
        swipeRefresh.setOnRefreshListener {
            usersViewModel.refreshUsers()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        usersViewModel.usersUI().observe(this, Observer { userListUI ->
            when (userListUI) {
                is Loading -> showLoading()
                is Error -> showError(userListUI.message)
                is Success -> showUsers()
            }
        })
        usersViewModel.userListItems().observe(this, Observer { userListItems ->
            gridAdapter.setUsers(userListItems)
        })
    }

    override fun needMoreData() {
        usersViewModel.loadUsers()
    }

    private fun showUsers() {
        swipeRefresh.isRefreshing = false
        loading.gone()
        recyclerview.visible()
        errorLayout.gone()
    }

    private fun showError(message: String?) {
        swipeRefresh.isRefreshing = false
        loading.gone()
        recyclerview.gone()
        errorLayout.visible()
        errorTextView.text = message
        Timber.e(message)
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        if (gridAdapter.isEmpty()) {
            loading.visible()
            errorLayout.gone()
        }
    }
}