package com.cbr.base.data.repository

import com.cbr.base.data.database.dao.ProjectDAO
import com.cbr.base.data.database.entity.ProjectEntity
import com.cbr.base.data.network.BehanceApiService
import com.cbr.base.model.domain.Project
import io.reactivex.Single
import java.net.UnknownHostException
import javax.inject.Inject


class ProjectRepository @Inject constructor(
        private val behanceApiService: BehanceApiService,
        private val projectDAO: ProjectDAO
) {

    fun loadProjects(map: Map<String, Any>): Single<List<Project>> {
        return behanceApiService.getProjects(map)
                .map { response ->
                    response.items.map { it.toModel() }
                }
                .doOnSuccess { items ->
                    projectDAO.insertAll(items.map { ProjectEntity.fromModel(it) })
                }
                .onErrorResumeNext { t: Throwable ->
                    if (t is UnknownHostException) {
                        projectDAO.getAll().toObservable()
                                .flatMapIterable { items -> items }
                                .map { item -> item.toModel() }
                                .toList()
                    } else {
                        Single.error(t)
                    }
                }
    }

    fun loadProjectById(id: String, map: Map<String, Any>) =
            behanceApiService.getProjectById(id, map)
                    .map { it.item.toModel() }

}