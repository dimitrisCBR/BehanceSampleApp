package com.cbr.behance.ui.project.di

import com.cbr.behance.ui.project.ProjectListFragment
import com.futureworkshops.domain.di.ApplicationComponent
import dagger.Component


@ProjectsScope
@Component(dependencies = [ApplicationComponent::class])
interface ProjectComponent {

    fun inject(projectListFragment: ProjectListFragment)

}