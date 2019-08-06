package com.cbr.behance.project.detail

import android.animation.AnimatorInflater
import android.os.Bundle
import android.transition.Transition
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cbr.base.date.DateFormatter
import com.cbr.base.extension.loadImage
import com.cbr.base.model.domain.Project
import com.cbr.base.text.TextProvider
import com.cbr.base.ui.BaseActivity
import com.cbr.behance.BehanceSampleApplication
import com.cbr.behance.R
import com.cbr.behance.project.di.DaggerProjectDetailsComponent
import com.cbr.behance.project.di.ProjectDetailsModule
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_project_details.*
import timber.log.Timber
import javax.inject.Inject

class ProjectDetailsActivity : BaseActivity() {

    override fun getLayout(): Int = R.layout.activity_project_details

    @Inject
    lateinit var projectDetailsVMFactory: ProjectDetailsVMFactory

    @Inject
    lateinit var dateFormatter: DateFormatter
    @Inject
    lateinit var textProvider: TextProvider

    lateinit var projectDetailsViewModel: ProjectDetailsViewModel

    private fun projectId() = intent?.extras?.getLong(EXTRA_PROJECT_ID) ?: 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerProjectDetailsComponent.builder()
                .applicationComponent(BehanceSampleApplication.appComponent)
                .projectDetailsModule(ProjectDetailsModule(projectId().toString()))
                .build()
                .inject(this)

        projectDetailsViewModel = ViewModelProviders.of(this, projectDetailsVMFactory)
                .get(ProjectDetailsViewModel::class.java)

        supportPostponeEnterTransition()

        projectDetailsViewModel.projectUI().observe(this, Observer {
            when (it) {
                is Success -> bindProject(it.project)
                is Error -> handleError(it.message)
                is Loading -> Timber.i("Loading Project")
            }
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        contentLayout.alpha = 0f
    }

    private fun handleError(message: String) {
        Toast.makeText(this, R.string.error_loading, Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun bindProject(project: Project) {

        window.sharedElementEnterTransition?.addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition?) {
                AnimatorInflater.loadAnimator(this@ProjectDetailsActivity, R.animator.animator_fade_in_up).apply {
                    duration = 200
                    interpolator = AccelerateDecelerateInterpolator()
                    setTarget(contentLayout)
                    start()
                }
            }

            override fun onTransitionResume(transition: Transition?) {
            }

            override fun onTransitionPause(transition: Transition?) {
            }

            override fun onTransitionCancel(transition: Transition?) {
            }

            override fun onTransitionStart(transition: Transition?) {
            }

        })
        startPostponedEnterTransition()
        projectTitleTextView.text = project.name
        imageView.loadImage(project.getCoverImage(), { supportStartPostponedEnterTransition() })
        project.field.takeIf { it.isNotEmpty() }?.let {
            it.forEach { field ->
                val chip = Chip(this)
                chip.text = field
                chip.isCheckable = false
                chip.isClickable = false
                chipGroup.addView(chip)
            }
        }
        dateTextView.text = dateFormatter.formatFullDate(project.creationDate)
        viewsTextView.text = textProvider.getFormattedString(R.string.formatter_views, project.stats.views)
        likesTextView.text = textProvider.getFormattedString(R.string.formatter_likes, project.stats.appreciations)
        commentsTextView.text = textProvider.getFormattedString(R.string.formatter_comments, project.stats.comments)
    }

    companion object {
        const val EXTRA_PROJECT_ID = "EXTRA_PROJECT_ID"
    }

}