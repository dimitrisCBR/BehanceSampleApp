package com.cbr.behance.project.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cbr.base.ui.BaseViewModel
import com.cbr.behance.commons.recycler.LoadingViewHolder
import com.cbr.behance.project.list.recycler.ProjectGridItem
import javax.inject.Inject


class ProjectListViewModel(
        private val projectsInteractor: ProjectListInteractor
) : BaseViewModel() {

    private val projectsLiveData = MutableLiveData<List<ProjectGridItem>>()
    private val projectStateLiveData = MutableLiveData<ProjectsUI>()

    init {
        loadProjects()
    }

    fun loadProjects() {
        if (projectStateLiveData.value == Loading) {
            return
        }
        projectStateLiveData.postValue(Loading)
        compositeDisposable.add(
                projectsInteractor.loadProjects()
                        .subscribe({
                            handleNewProjects(it)
                            projectStateLiveData.postValue(Success)
                        }, { t ->
                            projectStateLiveData.postValue(Error(t.message
                                    ?: ""))
                        })
        )
    }

    private fun handleNewProjects(projectItems: List<ProjectGridItem>) {
        val items = mutableListOf<ProjectGridItem>()
        val oldItems = projectsLiveData.value.orEmpty()

        items.addAll(oldItems.filter { item -> item.itemType != LoadingViewHolder.TYPE_LOADING })
        items.addAll(projectItems)
        items.add(ProjectGridItem(LoadingViewHolder.TYPE_LOADING, Unit))

        projectsLiveData.postValue(items)
    }

    fun refresh() {
        projectsInteractor.refresh()
        loadProjects()
    }

    fun projects(): LiveData<List<ProjectGridItem>> = projectsLiveData

    fun uiState(): LiveData<ProjectsUI> = projectStateLiveData

}

sealed class ProjectsUI
object Loading : ProjectsUI()
data class Error(val message: String) : ProjectsUI()
object Success : ProjectsUI()

class ProjectListVMFactory @Inject constructor(
        private val interactor: ProjectListInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProjectListViewModel(interactor) as T
    }

}