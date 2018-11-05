package com.cbr.behancesample.ui.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.cbr.behancesample.BehanceSampleApplication
import com.cbr.behancesample.R
import com.cbr.behancesample.ui.GridDecorator
import com.cbr.behancesample.util.UiUtils
import com.futureworkshops.data.model.domain.User
import com.futureworkshops.domain.Outcome
import kotlinx.android.synthetic.main.activity_users.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by dimitrios on 12/10/2018.
 */
class UsersActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: UserListViewModelFactory

    lateinit var usersViewModel: UserListViewModel

    val columnCount: Int
        get() {
            val itemWidth = (resources.getDimension(R.dimen.card_behace_user_item) + resources.getDimension(R.dimen.card_standard_padding)).toInt()
            return UiUtils.getScreenWidth(this) / itemWidth
        }

    val gridAdapter = UserGridAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        initDI()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        recyclerview.apply {
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

        usersViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserListViewModel::class.java)

        usersViewModel.outcome.observe(this, Observer { outcome ->
            when (outcome) {
                is Outcome.Progress -> showLoading()
                is Outcome.Error -> showError(outcome.throwable.message)
                is Outcome.Success -> showUsers(outcome.data)
            }
        })
        usersViewModel.getUsers()
    }

    private fun initDI() {
        DaggerUserListComponent.builder()
                .applicationComponent((application as BehanceSampleApplication).appComponent)
                .build()
                .inject(this)
    }

    private fun showUsers(data: List<User>?) {
        if (data?.isNotEmpty() == true) {
            gridAdapter.setUsers(data)
        } else {
            showError(getString(R.string.err_empty_data))
        }
    }

    private fun showError(message: String?) {
        Timber.e(message)
    }

    private fun showLoading() {

    }
}