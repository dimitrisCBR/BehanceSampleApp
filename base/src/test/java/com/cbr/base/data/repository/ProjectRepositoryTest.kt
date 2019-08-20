package com.cbr.base.data.repository

import com.cbr.base.data.database.dao.ProjectDAO
import com.cbr.base.data.network.BehanceApiService
import com.cbr.base.data.network.ListResponse
import com.cbr.base.data.network.ObjectResponse
import com.cbr.base.model.api.BehanceProject
import com.nhaarman.mockitokotlin2.times
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Matchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeoutException

class ProjectRepositoryTest {

    private lateinit var projectRepository: ProjectRepository


    @Mock
    lateinit var mockBehanceApi: BehanceApiService


    @Mock
    lateinit var mockProjectDAO: ProjectDAO

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        projectRepository = ProjectRepository(mockBehanceApi, mockProjectDAO)
    }

    @Test
    fun `load projects success`() {
        val responseList = listOf<BehanceProject>()
        val expectedList = responseList.map { it.toModel() }
        val response = ListResponse(responseList)

        Mockito.`when`(
                mockBehanceApi.getProjects(ArgumentMatchers.anyMap())
        ).thenReturn(
                Single.just(response))

        projectRepository.loadProjects(mapOf())
                .test()
                .assertValue(expectedList)
                .assertNoErrors()
                .dispose()

        verify(mockProjectDAO, times(1)).insertAll(ArgumentMatchers.anyList())
    }

    @Test
    fun `load projects error`() {
        val error = TimeoutException()
        Mockito.`when`(
                mockBehanceApi.getProjects(
                        ArgumentMatchers.anyMap())
        ).thenReturn(
                Single.error(error))

        projectRepository.loadProjects(mapOf())
                .test()
                .assertError(error)
                .dispose()
    }

    @Test
    fun `load projectById success`() {
        val project = mock(BehanceProject::class.java)
        val projectModel = project.toModel()
        val response = ObjectResponse(project)

        Mockito.`when`(
                mockBehanceApi.getProjectById(anyString(), ArgumentMatchers.anyMap())
        ).thenReturn(
                Single.just(response)
        )

        projectRepository.loadProjectById("100l", mapOf())
                .test()
                .assertValue(projectModel)
                .dispose()
    }

    @Test
    fun `load projectById error`() {
        val error = TimeoutException()
        Mockito.`when`(
                mockBehanceApi.getProjectById(anyString(), ArgumentMatchers.anyMap())
        ).thenReturn(
                Single.error(error)
        )

        projectRepository.loadProjectById("100l", mapOf())
                .test()
                .assertError(error)
                .dispose()
    }


}