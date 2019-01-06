package com.cbr.behance.ui.project

import com.cbr.behance.common.Outcome
import com.futureworkshops.data.model.domain.Project
import com.futureworkshops.domain.data.network.query.ProjectsQuery
import com.futureworkshops.domain.data.scheduler.SchedulersProvider
import com.futureworkshops.domain.domain.ProjectRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject


class ProjectListInteractor @Inject constructor(
        private val projectRepository: ProjectRepository,
        private val schedulersProvider: SchedulersProvider
) {

    val query = ProjectsQuery()

    fun loadProjects(): Observable<Outcome<List<Project>>> {
        return projectRepository.loadProjects(query.build())
                .map { projects -> Outcome.success(projects) }
                .onErrorResumeNext {
                    Single.just(Outcome.error(it.message ?: ""))
                }
                .doOnSuccess {
                    query.nextPage()
                }
                .toObservable()
                .startWith(Outcome.loading())

                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
    }


}