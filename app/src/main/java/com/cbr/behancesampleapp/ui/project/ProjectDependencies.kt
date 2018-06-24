package com.cbr.behancesampleapp.ui.project

import com.cbr.behancesampleapp.domain.dagger.AppComponent
import com.cbr.behancesampleapp.domain.dagger.scope.FragmentScope
import com.cbr.behancesampleapp.domain.network.repository.BehanceRepository
import com.cbr.behancesampleapp.ui.project.list.ProjectListFragment
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
class ProjectsModule {

    @Provides
    @FragmentScope
    fun provideProjectsInteractor(behanceRepository: BehanceRepository): ProjectsInteractor {
        return ProjectsInteractor(behanceRepository)
    }
}


@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = [ProjectsModule::class])
interface ProjectComponent {

    fun inject(projectListFragment: ProjectListFragment)

}