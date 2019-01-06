package com.cbr.behance.ui.project.recycler

import androidx.recyclerview.widget.DiffUtil
import com.futureworkshops.data.model.domain.Project

class ProjectsDiffCallback(
        private val oldProjects: List<Project>,
        private val newProjects: List<Project>) : DiffUtil.Callback() {

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean = oldProjects[p0].id == newProjects[p1].id

    override fun getOldListSize(): Int = oldProjects.size

    override fun getNewListSize(): Int = newProjects.size

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean = oldProjects[p0] == newProjects[p1]

}