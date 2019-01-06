package com.cbr.behance.ui.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cbr.behance.common.BaseViewModel
import com.cbr.behance.common.Outcome
import com.futureworkshops.data.model.domain.Project
import javax.inject.Inject


class ProjectListViewModel(
        private val projectsInteractor: ProjectListInteractor
) : BaseViewModel() {

    private val projectsLiveData = MutableLiveData<Outcome<List<Project>>>()

    init {
        loadProjects()
    }

    fun loadProjects() {
        compositeDisposable.add(
                projectsInteractor.loadProjects()
                        .subscribe({
                            projectsLiveData.postValue(it)
                        }, { t ->
                            projectsLiveData.postValue(Outcome.error(t.message ?: ""))
                        })
        )
    }

    fun refresh() {
        projectsInteractor.refresh()
        loadProjects()
    }

    fun projects(): LiveData<Outcome<List<Project>>> = projectsLiveData

}

class ProjectListVMFactory @Inject constructor(
        private val interactor: ProjectListInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProjectListViewModel(interactor) as T
    }

}