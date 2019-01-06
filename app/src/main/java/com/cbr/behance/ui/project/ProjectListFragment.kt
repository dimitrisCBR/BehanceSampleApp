package com.cbr.behance.ui.project

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.cbr.behance.BehanceSampleApplication
import com.cbr.behance.R
import com.cbr.behance.common.Outcome
import com.cbr.behance.common.recycler.GridDecorator
import com.cbr.behance.common.recycler.PagingAdapter
import com.cbr.behance.ui.project.di.DaggerProjectComponent
import com.cbr.behance.ui.project.recycler.ProjectsGridAdapter
import com.futureworkshops.data.model.domain.Project
import com.futureworkshops.domain.extension.gone
import com.futureworkshops.domain.extension.screenWidth
import com.futureworkshops.domain.extension.visible
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerview.apply {
            val itemWidth = (resources.getDimension(R.dimen.card_behace_user_item) + resources.getDimension(R.dimen.card_standard_padding)).toInt()
            val columnCount = context.screenWidth() / itemWidth
            val gridLayoutManager = GridLayoutManager(context, columnCount)
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int = gridAdapter.getSpanForPosition(position)
            }
            layoutManager = gridLayoutManager
            addItemDecoration(GridDecorator(context, columnCount))
            gridAdapter = ProjectsGridAdapter(this@ProjectListFragment)
            adapter = gridAdapter
        }

        projectListViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ProjectListViewModel::class.java)

        projectListViewModel.projects().observe(this, Observer { outcome ->
            when (outcome) {
                is Outcome.Progress -> showLoading()
                is Outcome.Error -> showError(outcome.errorMsg)
                is Outcome.Success -> showProjects(outcome.data)
            }
        })
    }

    override fun needMoreData() {
        projectListViewModel.loadProjects()
    }

    private fun showProjects(data: List<Project>?) {
        if (data?.isNotEmpty() == true) {
            loading.gone()
            recyclerview.visible()
            errorLayout.gone()
            gridAdapter.setProjects(data)
        } else {
            showError(getString(R.string.err_empty_data))
        }
    }

    private fun showError(message: String?) {
        Timber.e(message)
        loading.gone()
        recyclerview.gone()
        errorLayout.visible()
        errorTextView.text = message
    }

    private fun showLoading() {
        loading.visible()
        errorLayout.gone()
    }
}
