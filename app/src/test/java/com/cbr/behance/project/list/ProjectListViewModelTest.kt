package com.cbr.behance.project.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cbr.behance.project.list.recycler.ProjectGridItem
import io.reactivex.Single
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ProjectListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var projectListInteractor: ProjectListInteractor

    lateinit var projectListVM: ProjectListViewModel

    @Mock
    lateinit var mockObserver: Observer<List<ProjectGridItem>>

    @Mock
    lateinit var mockStateObserver: Observer<ProjectsUI>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        projectListVM = ProjectListViewModel(projectListInteractor)
        projectListVM.projects().observeForever(mockObserver)
        projectListVM.uiState().observeForever(mockStateObserver)
    }

    @Test
    fun `load projects success`() {
        val resultItems = mutableListOf<ProjectGridItem>()
        Mockito.`when`(projectListInteractor.loadProjects())
                .thenReturn(Single.just(resultItems))
        projectListVM.loadProjects()

        Assert.assertNotNull(projectListVM.uiState().value)
        Assert.assertEquals(projectListVM.uiState().value, Success)
    }


    @Test
    fun `load projects error`() {

        val error = Throwable("Something went wrong")

        Mockito.`when`(projectListInteractor.loadProjects())
                .thenReturn(Single.error(error))
        projectListVM.loadProjects()

        Assert.assertNotNull(projectListVM.uiState().value)
        Assert.assertEquals(projectListVM.uiState().value, Error(error.message ?: ""))
    }


    @Test
    fun `handleNewProjects behavior`() {

        val oldItems = listOf(
                ProjectGridItem(ProjectGridItem.TYPE_PROJECT, 1),
                ProjectGridItem(ProjectGridItem.TYPE_PROJECT, 2),
                ProjectGridItem(ProjectGridItem.TYPE_PROJECT, 3)
        )

        Mockito.`when`(projectListInteractor.loadProjects())
                .thenReturn(Single.just(oldItems.toMutableList()))

        val subject = Mockito.spy(projectListVM)
        subject.loadProjects()

        assertTrue(subject.projects().value?.last()?.isLoading() ?: false)
        assertTrue(subject.projects().value?.size ?: 0 == (oldItems.size + 1))


    }

}