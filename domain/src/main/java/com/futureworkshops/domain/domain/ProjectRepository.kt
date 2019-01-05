package com.futureworkshops.domain.domain

import com.futureworkshops.data.model.domain.Project
import com.futureworkshops.domain.data.database.dao.ProjectDAO
import com.futureworkshops.domain.data.network.BehanceApiService
import com.futureworkshops.domain.data.scheduler.SchedulersProvider
import io.reactivex.Single
import javax.inject.Inject


class ProjectRepository @Inject constructor(
        private val behanceApiService: BehanceApiService,
        private val projectDAO: ProjectDAO,
        private val appSchedulersProvider: SchedulersProvider
) {

    //TODO
    fun loadProjects(map : Map<String,Any>) : Single<List<Project>> {
        return Single.error(Throwable("Not implemented"))
    }
}