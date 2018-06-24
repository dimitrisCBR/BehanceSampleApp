package com.cbr.behancesampleapp.ui.project.list

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cbr.behancesampleapp.R
import com.cbr.behancesampleapp.model.BehanceProject
import com.cbr.behancesampleapp.ui.common.MvpView
import com.cbr.behancesampleapp.ui.common.mvp.BaseMvpFragment
import com.cbr.behancesampleapp.ui.common.recycler.NormalDecorator
import com.cbr.behancesampleapp.ui.common.recycler.PagingAdapter
import com.cbr.behancesampleapp.ui.project.DaggerProjectComponent
import com.cbr.behancesampleapp.ui.project.ProjectsModule
import com.cbr.behancesampleapp.ui.user.details.UserDetailsActivity
import kotlinx.android.synthetic.main.include_appbarlayout.*
import kotlinx.android.synthetic.main.include_layout_empty.*
import kotlinx.android.synthetic.main.include_layout_progress.*
import kotlinx.android.synthetic.main.include_layout_recyclerview.*
import javax.inject.Inject

interface ProjectListView : MvpView {

    fun showError(errorMsg: String?)

    fun showLoading()

    fun showContent()

    fun onProjectsFetched(items: List<BehanceProject>)

}

class ProjectListFragment : BaseMvpFragment(), ProjectListView, PagingAdapter.Interactor<BehanceProject> {

    @Inject
    lateinit var presenter: ProjectListPresenter

    private var projectsAdapter = ProjectsAdapter(this)

    override fun onFragmentInject() {
        DaggerProjectComponent.builder()
            .appComponent(appComponent())
            .projectsModule(ProjectsModule())
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

    override fun onStop() {
        super.onStop()
        presenter.onUnsubscribe()
    }

    override fun onStart() {
        super.onStart()
        presenter.onSubscribe()
        requestMoreData()
    }

    private fun bindViews() {
        toolbar.setTitle(R.string.title_projects)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(NormalDecorator(context))
            adapter = projectsAdapter
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
        presenter.requestProjects()
    }

    override fun onProjectsFetched(items: List<BehanceProject>) {
        showContent()
        projectsAdapter.onDataLoaded(items)
    }

    override fun onListItemClicked(item: BehanceProject, position: Int) {
        startActivity(UserDetailsActivity.newIntent(context, item.id))
    }


    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        /* Take in account the loading viewholder */
        if (projectsAdapter.itemCount <= 1) {
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