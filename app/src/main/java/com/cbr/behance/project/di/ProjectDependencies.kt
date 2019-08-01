package com.cbr.behance.project.di

import com.cbr.base.di.ApplicationComponent
import com.cbr.behance.project.ProjectListFragment
import dagger.Component


@ProjectsScope
@Component(dependencies = [ApplicationComponent::class])
interface ProjectComponent {

    fun inject(projectListFragment: ProjectListFragment)

}