package com.cbr.behance.project.di

import com.cbr.base.di.ApplicationComponent
import com.cbr.behance.project.detail.ProjectDetailsFragment
import com.cbr.behance.project.detail.ProjectDetailsInteractor
import com.cbr.behance.project.detail.ProjectDetailsVMFactory
import com.cbr.behance.project.detail.ProjectDetailsViewModel
import dagger.Component
import dagger.Module
import dagger.Provides


@ProjectsScope
@Component(dependencies = [ApplicationComponent::class], modules = [ProjectDetailsModule::class])
interface ProjectDetailsComponent {

    fun inject(projectDetailsFragment: ProjectDetailsFragment)

}

@Module
class ProjectDetailsModule(private val projectId: String) {

    @Provides
    fun projectDetailsVMFactory(projectDetailsInteractor: ProjectDetailsInteractor) = ProjectDetailsVMFactory(projectDetailsInteractor, projectId)
}