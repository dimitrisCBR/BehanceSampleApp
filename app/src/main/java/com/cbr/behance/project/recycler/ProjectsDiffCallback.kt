package com.cbr.behance.project.recycler

import androidx.recyclerview.widget.DiffUtil

class ProjectsDiffCallback(
        private val oldProjects: List<ProjectGridItem>,
        private val newProjects: List<ProjectGridItem>) : DiffUtil.Callback() {

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean = (oldProjects[p0].isLoading() && newProjects[p1].isLoading())
            || (!oldProjects[p0].isLoading() && !newProjects[p1].isLoading() && oldProjects[p0].project().id == newProjects[p1].project().id)

    override fun getOldListSize(): Int = oldProjects.size

    override fun getNewListSize(): Int = newProjects.size

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean = oldProjects[p0] == newProjects[p1]

}