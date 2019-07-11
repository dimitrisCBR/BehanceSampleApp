package com.cbr.behance.project.di

import com.cbr.behance.project.ProjectListFragment
import com.futureworkshops.core.di.ApplicationComponent
import dagger.Component


@ProjectsScope
@Component(dependencies = [ApplicationComponent::class])
interface ProjectComponent {

    fun inject(projectListFragment: ProjectListFragment)

}