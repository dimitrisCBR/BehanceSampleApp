package com.cbr.behance.project.list

import com.cbr.base.data.network.query.ApiQuery
import com.cbr.base.data.repository.ProjectRepository
import com.cbr.base.data.scheduler.TestSchedulerProvider
import com.cbr.base.model.api.BehanceProject
import com.cbr.behance.project.list.recycler.ProjectGridItem
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyMap
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ProjectListInteractorTest {


    lateinit var projectListInteractor: ProjectListInteractor

    @Mock
    lateinit var mockProjectsRepo: ProjectRepository

    val schedulersProvider = TestSchedulerProvider()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        projectListInteractor = ProjectListInteractor(mockProjectsRepo, schedulersProvider)
    }


    @Test
    fun `load Projects success`() {
        val responseList = listOf<BehanceProject>()
        val expectedList = responseList.map { it.toModel() }

        Mockito.`when`(
                mockProjectsRepo.loadProjects(anyMap())
        ).thenReturn(
                Single.just(expectedList)
        )

        projectListInteractor.loadProjects()
                .test()
                .assertValue(
                        expectedList
                                .map { ProjectGridItem(ProjectGridItem.TYPE_PROJECT, it) }
                                .toMutableList()
                )
                .dispose()
    }

    @Test
    fun `pagination works`() {
        val pageNumber = projectListInteractor.query.getPage()

        Mockito.`when`(mockProjectsRepo.loadProjects(anyMap()))
                .thenReturn(
                        Single.just(mutableListOf())
                )

        projectListInteractor.loadProjects().blockingGet()
        assert(projectListInteractor.query.getPage() == (pageNumber + 1))
    }

    @Test
    fun `reset query page`() {
        projectListInteractor.query.nextPage()
        projectListInteractor.refresh()
        assert(projectListInteractor.query.getPage() == ApiQuery.DEFAULT_PAGE)
    }
}