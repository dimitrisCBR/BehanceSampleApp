package com.cbr.behance.project.detail

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cbr.base.extension.loadImage
import com.cbr.base.model.domain.Project
import com.cbr.base.ui.BaseActivity
import com.cbr.behance.BehanceSampleApplication
import com.cbr.behance.R
import com.cbr.behance.project.di.DaggerProjectDetailsComponent
import com.cbr.behance.project.di.ProjectDetailsModule
import kotlinx.android.synthetic.main.activity_project_details.*
import timber.log.Timber
import javax.inject.Inject

class ProjectDetailsActivity : BaseActivity() {

    override fun getLayout(): Int = R.layout.activity_project_details

    @Inject
    lateinit var projectDetailsVMFactory: ProjectDetailsVMFactory

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

    private fun handleError(message: String) {
        Toast.makeText(this, R.string.error_loading, Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun bindProject(project: Project) {
        projectTitleTextView.text = project.name
        projectImageView.loadImage(project.getCoverImage(), { supportStartPostponedEnterTransition() })
    }

    companion object {
        const val EXTRA_PROJECT_ID = "EXTRA_PROJECT_ID"
    }

}