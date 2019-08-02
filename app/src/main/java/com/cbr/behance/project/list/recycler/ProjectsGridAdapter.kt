package com.cbr.behance.project.list.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cbr.base.extension.loadImage
import com.cbr.base.model.domain.Project
import com.cbr.behance.R
import com.cbr.behance.commons.recycler.BaseVH
import com.cbr.behance.commons.recycler.ListItem
import com.cbr.behance.commons.recycler.LoadingViewHolder
import com.cbr.behance.commons.recycler.LoadingViewHolder.Companion.TYPE_LOADING
import com.cbr.behance.commons.recycler.PagingAdapter
import kotlinx.android.synthetic.main.viewholder_grid.*

class ProjectsGridAdapter(val columnCount: Int, callback: Callback, val projectCB: ProjectCallback) : PagingAdapter<ProjectGridItem>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH<ProjectGridItem> {
        return when (viewType) {
            LoadingViewHolder.TYPE_LOADING -> LoadingViewHolder.newInstance(parent)
            else -> ProjectGridViewHolder.newInstance(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (holder is ProjectGridViewHolder) {
            holder.bind(position, items[position])
            holder.itemView.setOnClickListener {
                projectCB.onProjectTapped(items[position].project())
            }
        }
    }

    fun setProjects(newProjects: List<ProjectGridItem>) {
        val diffResult = DiffUtil.calculateDiff(ProjectsDiffCallback(items, newProjects))
        diffResult.dispatchUpdatesTo(this)
        items.clear()
        items.addAll(newProjects)
    }

    fun getSpanForPosition(position: Int): Int = if (getItemViewType(position) == TYPE_LOADING) columnCount else 1

    fun isEmpty(): Boolean = items.size <= 1

}

class ProjectGridViewHolder(view: View) : BaseVH<ProjectGridItem>(view) {

    override fun bind(position: Int, data: ProjectGridItem) {
        val project = data.project()
        project.covers.takeIf { it.isNotEmpty() }?.let {
            val key = it.keys.first()
            imageView.loadImage(it.get(key))
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

class ProjectGridItem(type: Int, data: Any) : ListItem(type, data) {

    fun project() = data as Project

    companion object {
        const val TYPE_PROJECT = 0
    }
}

interface ProjectCallback {

    fun onProjectTapped(project: Project)
}