package com.cbr.behancesampleapp.ui.project.list

import com.cbr.behancesampleapp.domain.network.query.ProjectsQuery
import com.cbr.behancesampleapp.ui.common.mvp.BasePresenter
import com.cbr.behancesampleapp.ui.project.ProjectsInteractor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class ProjectListPresenter
@Inject
constructor(val projectsInteractor: ProjectsInteractor,
            compositeDisposable: CompositeDisposable) : BasePresenter<ProjectListView>(compositeDisposable) {

    private val projectQuery = ProjectsQuery()

    fun requestProjects() {
        view?.showLoading()
        projectsInteractor.getProjects(projectQuery)
            .subscribe({ response ->
                projectQuery.nextPage()
                view?.onProjectsFetched(response.items)
            }, { t -> view?.showError(t.message) })
    }

    fun refresh() {
        projectQuery.reset()
        requestProjects()
    }

}