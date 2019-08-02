package com.cbr.behance.project.detail

import com.cbr.base.data.network.query.ApiQuery
import com.cbr.base.data.repository.ProjectRepository
import com.cbr.base.data.scheduler.SchedulersProvider
import javax.inject.Inject

class ProjectDetailsInteractor @Inject constructor(
        private val projectRepository: ProjectRepository,
        private val schedulersProvider: SchedulersProvider) {

    val apiQuery = ApiQuery()

    fun getProjectDetails(id: String) = projectRepository.loadProjectById(id, apiQuery.build())
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())

}