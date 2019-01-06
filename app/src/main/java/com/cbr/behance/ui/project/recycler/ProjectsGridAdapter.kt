package com.cbr.behance.ui.project.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cbr.behance.R
import com.cbr.behance.common.recycler.BaseVH
import com.futureworkshops.data.model.domain.Project
import com.futureworkshops.domain.extension.loadImage
import kotlinx.android.synthetic.main.viewholder_grid.*

class ProjectsGridAdapter : RecyclerView.Adapter<ProjectGridViewHolder>() {

    var items: ArrayList<Project> = arrayListOf()

    override fun onBindViewHolder(holder: ProjectGridViewHolder, position: Int) {
        holder.onBind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectGridViewHolder {
        return ProjectGridViewHolder.newInstance(parent)
    }

    fun setProjects(newProjects: List<Project>) {
        val diffResult = DiffUtil.calculateDiff(ProjectsDiffCallback(items, newProjects))
        diffResult.dispatchUpdatesTo(this)
        items.clear()
        items.addAll(newProjects)
    }

    fun getSpanForPosition(position: Int): Int = 1

}

class ProjectGridViewHolder(val view: View) : BaseVH(view) {

    fun onBind(project: Project, position: Int) {
        project.covers.takeIf { it.isNotEmpty() }.let {
            val key = it?.keys?.first()
            imageView.loadImage(it?.get(key))
        }

        titleTextView.text = project.name
        subtitleTextView.text = project.description
        val hasExtraInfo = project.field.isNotEmpty()
        infoDivider.visibility = if (hasExtraInfo) View.GONE else View.VISIBLE
        extraTextView.visibility = if (hasExtraInfo) View.GONE else View.VISIBLE
        val extras = project.field.toString()
        extraTextView.text = extras
    }

    companion object {

        fun newInstance(parent: ViewGroup): ProjectGridViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_grid, parent, false)
            return ProjectGridViewHolder(view)
        }
    }
}