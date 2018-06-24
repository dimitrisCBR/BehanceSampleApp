package com.cbr.behancesampleapp.ui.project

import com.cbr.behancesampleapp.domain.network.query.ProjectsQuery
import com.cbr.behancesampleapp.domain.network.repository.BehanceRepository
import com.cbr.behancesampleapp.model.BehanceListResponse
import com.cbr.behancesampleapp.model.BehanceProject
import io.reactivex.Observable

class ProjectsInteractor(val repository: BehanceRepository) {

    fun getProjects(projectsQuery: ProjectsQuery): Observable<BehanceListResponse<BehanceProject>> {
        return repository.getProjects(projectsQuery.build())
    }

}