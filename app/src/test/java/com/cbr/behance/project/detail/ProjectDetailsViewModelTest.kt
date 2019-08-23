package com.cbr.behance.project.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cbr.base.model.domain.Project
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ProjectDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var projectDetailsViewModel: ProjectDetailsViewModel

    @Mock
    lateinit var mockInteractor: ProjectDetailsInteractor

    @Mock
    lateinit var mockObserver: Observer<ProjectDetailsUI>

    @Mock
    lateinit var mockProject: Project

    val PROJECT_ID = "123"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        projectDetailsViewModel = ProjectDetailsViewModel((mockInteractor), PROJECT_ID)
        projectDetailsViewModel.projectDetailsState.observeForever(mockObserver)
    }

    @Test
    fun `loadProject success`() {

        Mockito.`when`(mockInteractor.getProjectDetails(PROJECT_ID))
                .thenReturn(Single.just(mockProject))
        projectDetailsViewModel.loadProject()
        assertEquals(projectDetailsViewModel.projectDetailsState.value, Success(mockProject))
    }

    @Test
    fun `loadProject error`() {

        val error = Throwable("Something went wrong")

        Mockito.`when`(mockInteractor.getProjectDetails(PROJECT_ID))
                .thenReturn(Single.error(error))
        projectDetailsViewModel.loadProject()
        assertEquals(projectDetailsViewModel.projectDetailsState.value, Error(error.message ?: ""))
    }
}