package com.cbr.behance.project


import com.cbr.base.data.network.query.ProjectsQuery
import com.cbr.base.data.repository.ProjectRepository
import com.cbr.base.data.scheduler.SchedulersProvider
import com.cbr.behance.project.recycler.ProjectGridItem
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject


class ProjectListInteractor @Inject constructor(
        private val projectRepository: ProjectRepository,
        private val schedulersProvider: SchedulersProvider
) {

    private val query = ProjectsQuery()


    fun loadProjects(): Single<MutableList<ProjectGridItem>> =
            projectRepository.loadProjects(query.build())
                    .doOnSuccess {
                        query.nextPage()
                    }
                    .flatMapObservable { projects -> Observable.fromIterable(projects) }
                    .map { project -> ProjectGridItem(ProjectGridItem.TYPE_PROJECT, project) }
                    .toList()
                    .subscribeOn(schedulersProvider.io())
                    .observeOn(schedulersProvider.ui())


    fun refresh() {
        query.reset()
    }

}