package com.cbr.behance.project.list.recycler

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cbr.base.extension.loadImage
import com.cbr.base.model.domain.Project
import com.cbr.behance.R
import com.cbr.behance.commons.recycler.BaseVH
import com.cbr.behance.commons.recycler.ListItem
import com.cbr.behance.commons.recycler.LoadingViewHolder
import com.cbr.behance.commons.recycler.PagingAdapter
import com.cbr.behance.project.detail.ProjectDetailsActivity
import com.cbr.behance.project.list.recycler.ProjectGridItem.Companion.TYPE_PROJECT
import kotlinx.android.synthetic.main.activity_project_details.*

class ProjectsGridAdapter(callback: Callback, val hostActivity: Activity) : PagingAdapter<ProjectGridItem>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH<ProjectGridItem> {
        return when (viewType) {
            LoadingViewHolder.TYPE_LOADING -> LoadingViewHolder.newInstance(parent)
            else -> ProjectGridViewHolder.newInstance(parent) { data -> openProjectDetails(data) }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        when (getItemViewType(position)) {
            TYPE_PROJECT -> (holder as ProjectGridViewHolder).bind(position, items[position])
        }
    }

    private fun openProjectDetails(data: ProjectItemTransition) {
        val intent = Intent(hostActivity, ProjectDetailsActivity::class.java)
        intent.putExtra(ProjectDetailsActivity.EXTRA_PROJECT_ID, data.projectId)

//        hostActivity.setExitSharedElementCallback(object : SharedElementCallback() {
//            override fun onSharedElementStart(sharedElementNames: MutableList<String>, sharedElements: MutableList<View>, sharedElementSnapshots: MutableList<View>) {
//                hostActivity.setExitSharedElementCallback(null)
//                notifyItemChanged(data.position)
//            }
//        })

        val options = ActivityOptions.makeSceneTransitionAnimation(hostActivity, *data.sharedElements)
        hostActivity.startActivity(intent, options.toBundle())
    }

    fun setProjects(newProjects: List<ProjectGridItem>) {
        val diffResult = DiffUtil.calculateDiff(ProjectsDiffCallback(items, newProjects))
        diffResult.dispatchUpdatesTo(this)
        items.clear()
        items.addAll(newProjects)
    }

    fun isEmpty(): Boolean = items.size <= 1

}

class ProjectGridViewHolder(view: View, val clickCB: (transition: ProjectItemTransition) -> Unit) : BaseVH<ProjectGridItem>(view) {

    private val imageView: ImageView = itemView.findViewById(R.id.imageView)

    var project: Project? = null

    init {
        itemView.setOnClickListener {
            project?.let {
                val ctx = itemView.context
                imageView.transitionName = ctx.getString(R.string.transition_project_image)
                imageView.transitionName = ctx.getString(R.string.transition_project_background)
                val data = ProjectItemTransition(
                        it.name,
                        it.id,
                        adapterPosition,
                        getSharedElementsForTransition(),
                        itemView
                )
                clickCB(data)
            }
        }
    }

    private fun getSharedElementsForTransition(): Array<Pair<View, String>> {
        val resources = itemView.context.resources
        return arrayOf(
                Pair(imageView as View, resources.getString(R.string.transition_project_image)),
                Pair(itemView, resources.getString(R.string.transition_project_background))
        )
    }

    override fun bind(position: Int, data: ProjectGridItem) {
        project = data.project()

        project?.let { project ->
            imageView.loadImage(project.getCoverImage())
            val colorBg = when (position % 3) {
                0 -> itemView.context.getColor(R.color.grey_light)
                1 -> itemView.context.getColor(R.color.grey_warm)
                else -> itemView.context.getColor(R.color.grey_dark)
            }
            background.setBackgroundColor(colorBg)

//            titleTextView.text = project.name
//            subtitleTextView.text = project.owners.first().displayName
//            val hasExtraInfo = project.field.isNotEmpty()
//            infoDivider.visibility = if (hasExtraInfo) View.VISIBLE else View.GONE
//            extraTextView.visibility = if (hasExtraInfo) View.VISIBLE else View.GONE
//            val extras = project.field.first()
//            extraTextView.text = extras
        }
    }

    companion object {

        fun newInstance(parent: ViewGroup, clickCB: (transition: ProjectItemTransition) -> Unit): ProjectGridViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_grid, parent, false)
            return ProjectGridViewHolder(view, clickCB)
        }
    }
}

class ProjectGridItem(type: Int, data: Any) : ListItem(type, data) {

    fun project() = data as Project

    companion object {

        const val TYPE_PROJECT = 0
    }
}

data class ProjectItemTransition(
        val title: String,
        val projectId: Long,
        val position: Int,
        val sharedElements: Array<Pair<View, String>>,
        val itemView: View
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProjectItemTransition

        if (title != other.title) return false
        if (projectId != other.projectId) return false
        if (position != other.position) return false
        if (!sharedElements.contentEquals(other.sharedElements)) return false
        if (itemView != other.itemView) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + projectId.hashCode()
        result = 31 * result + position
        result = 31 * result + sharedElements.contentHashCode()
        result = 31 * result + itemView.hashCode()
        return result
    }

}