package com.cbr.behance.project.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cbr.base.model.domain.Project
import com.cbr.base.ui.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class ProjectDetailsViewModel(
        private val projectDetailsInteractor: ProjectDetailsInteractor,
        private val projectId: String
) : BaseViewModel() {

    val projectDetailsState = MutableLiveData<ProjectDetailsUI>()


    fun loadProject() {
        compositeDisposable.add(
                projectDetailsInteractor.getProjectDetails(projectId)
                        .subscribe(
                                { project ->
                                    projectDetailsState.postValue(Success(project))
                                },
                                { t ->
                                    projectDetailsState.postValue(Error(t.message ?: ""))
                                    Timber.e(t)
                                }
                        )
        )
    }

    fun projectUI(): LiveData<ProjectDetailsUI> = projectDetailsState

}

sealed class ProjectDetailsUI
object Loading : ProjectDetailsUI()
data class Error(val message: String) : ProjectDetailsUI()
data class Success(val project: Project) : ProjectDetailsUI()

class ProjectDetailsVMFactory @Inject constructor(
        private val interactor: ProjectDetailsInteractor,
        private val projectId: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProjectDetailsViewModel(interactor, projectId) as T
    }

}