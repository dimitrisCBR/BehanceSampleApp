package com.cbr.behance.project


import com.futureworkshops.core.ui.Outcome
import com.futureworkshops.core.model.domain.Project
import com.futureworkshops.core.data.network.query.ProjectsQuery
import com.futureworkshops.core.data.repository.ProjectRepository
import com.futureworkshops.core.data.scheduler.SchedulersProvider
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject


class ProjectListInteractor @Inject constructor(
        private val projectRepository: ProjectRepository,
        private val schedulersProvider: SchedulersProvider
) {

    private val query = ProjectsQuery()
    private val allProjects = mutableListOf<Project>()

    fun loadProjects(): Observable<Outcome<List<Project>>> =
            projectRepository.loadProjects(query.build())
                    .doOnSuccess {
                        query.nextPage()
                        allProjects.addAll(it)
                    }
                    .flatMap { Single.just(allProjects.toList()) }
                    .map { projects -> Outcome.success(projects) }
                    .onErrorResumeNext { t: Throwable ->
                        Single.just(Outcome.error(t.message ?: ""))
                    }
                    .toObservable()
                    .startWith(Outcome.loading())
                    .subscribeOn(schedulersProvider.io())
                    .observeOn(schedulersProvider.ui())


    fun refresh() {
        query.reset()
        allProjects.clear()
    }

}