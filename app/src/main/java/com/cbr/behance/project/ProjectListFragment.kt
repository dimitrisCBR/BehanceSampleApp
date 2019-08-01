package com.cbr.behance.project

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
import com.cbr.behance.BehanceSampleApplication
import com.cbr.behance.R
import com.cbr.behance.commons.recycler.PagingAdapter
import com.cbr.behance.commons.recycler.decorator.GridDecorator
import com.cbr.behance.project.di.DaggerProjectComponent
import com.cbr.behance.project.recycler.ProjectsGridAdapter
import com.futureworkshops.core.extension.gone
import com.futureworkshops.core.extension.screenWidth
import com.futureworkshops.core.extension.visible
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.include_error_layout.*
import kotlinx.android.synthetic.main.include_loading.*
import timber.log.Timber
import javax.inject.Inject


class ProjectListFragment : Fragment(), PagingAdapter.Callback {

    @Inject
    lateinit var viewModelFactory: ProjectListVMFactory

    private lateinit var projectListViewModel: ProjectListViewModel

    lateinit var gridAdapter: ProjectsGridAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        DaggerProjectComponent
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
            gridAdapter = ProjectsGridAdapter(columnCount, this@ProjectListFragment)
            adapter = gridAdapter
        }
        swipeRefresh.setOnRefreshListener {
            projectListViewModel.refresh()
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        projectListViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ProjectListViewModel::class.java)

        projectListViewModel.projects().observe(this, Observer { projectListItems ->
            gridAdapter.setProjects(projectListItems)
        })
        projectListViewModel.uiState().observe(this, Observer { state ->
            when (state) {
                is Loading -> showLoading()
                is Error -> showError(state.message)
                is Success -> showProjects()
            }
        })
    }

    override fun needMoreData() {
        projectListViewModel.loadProjects()
    }

    private fun showProjects() {
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
