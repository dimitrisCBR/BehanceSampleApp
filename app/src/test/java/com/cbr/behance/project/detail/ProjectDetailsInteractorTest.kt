package com.cbr.behance.project.detail

import com.cbr.base.data.repository.ProjectRepository
import com.cbr.base.data.scheduler.TestSchedulerProvider
import com.cbr.base.model.domain.Project
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyMap
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeoutException


class ProjectDetailsInteractorTest {

    lateinit var projectDetailsInteractor: ProjectDetailsInteractor

    @Mock
    lateinit var projectRepository: ProjectRepository

    @Before
    public fun setup() {
        MockitoAnnotations.initMocks(this)
        projectDetailsInteractor = ProjectDetailsInteractor(projectRepository, TestSchedulerProvider())
    }

    @Test
    fun `getProjectDetails success`() {

        val mockProject = mock<Project>()
        Mockito.`when`(projectRepository.loadProjectById(anyString(), anyMap()))
                .thenReturn(Single.just(mockProject))

        projectDetailsInteractor.getProjectDetails("123")
                .test()
                .assertNoErrors()
                .assertValue(mockProject)
                .dispose()
    }

    @Test
    fun `getProjectDetails error`() {

        val error = TimeoutException()

        Mockito.`when`(projectRepository.loadProjectById(anyString(), anyMap()))
                .thenReturn(Single.error(error))

        projectDetailsInteractor.getProjectDetails("123")
                .test()
                .assertError(error)
                .dispose()
    }
}