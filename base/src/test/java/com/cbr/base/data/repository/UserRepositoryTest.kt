package com.cbr.base.data.repository

import com.cbr.base.data.database.dao.UserDAO
import com.cbr.base.data.network.BehanceApiService
import com.cbr.base.data.network.ListResponse
import com.cbr.base.model.api.BehanceUser
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeoutException

class UserRepositoryTest {


    private lateinit var userRepository: UserRepository


    @Mock
    lateinit var mockBehanceApi: BehanceApiService


    @Mock
    lateinit var mockUserDAO: UserDAO

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepository(mockBehanceApi, mockUserDAO)
    }

    @Test
    fun `load users success`() {
        val responseList = listOf<BehanceUser>()
        val expectedList = responseList.map { it.toModel() }
        val expectedResponse = ListResponse(responseList)

        Mockito.`when`(
                mockBehanceApi.getUsers(Matchers.anyMapOf(String::class.java, Any::class.java))
        ).thenReturn(
                Single.just(expectedResponse))

        userRepository.loadUsers(mapOf())
                .test()
                .assertValue(expectedList)
    }

    @Test
    fun `load projects error`() {
        val error = TimeoutException()
        Mockito.`when`(
                mockBehanceApi.getProjects(Matchers.anyMapOf(String::class.java, Any::class.java))
        ).thenReturn(
                Single.error(error))

        userRepository.loadUsers(mapOf())
                .test()
                .assertError(error)
    }


}