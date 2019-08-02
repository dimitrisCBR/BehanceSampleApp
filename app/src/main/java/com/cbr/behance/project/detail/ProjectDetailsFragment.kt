package com.cbr.behance.project.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.cbr.behance.BehanceSampleApplication
import com.cbr.behance.R
import com.cbr.behance.project.di.DaggerProjectDetailsComponent
import com.cbr.behance.project.di.ProjectDetailsModule
import javax.inject.Inject

class ProjectDetailsFragment : Fragment() {

    @Inject
    lateinit var projectDetailsVMFactory: ProjectDetailsVMFactory

    lateinit var projectDetailsViewModel: ProjectDetailsViewModel

    private val projectId: Long
        get() = ProjectDetailsFragmentArgs.fromBundle(arguments ?: Bundle()).projectId

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerProjectDetailsComponent.builder()
                .applicationComponent(BehanceSampleApplication.appComponent)
                .projectDetailsModule(ProjectDetailsModule(projectId.toString()))
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_project_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        projectDetailsViewModel = ViewModelProviders.of(this, projectDetailsVMFactory)
                .get(ProjectDetailsViewModel::class.java)
    }
}