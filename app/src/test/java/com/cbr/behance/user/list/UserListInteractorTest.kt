package com.cbr.behance.user.list

import com.cbr.base.data.repository.UserRepository
import com.cbr.base.data.scheduler.TestSchedulerProvider
import com.cbr.base.model.api.BehanceUser
import com.cbr.behance.user.list.recycler.UserGridItem
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyMap
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeoutException

class UserListInteractorTest {

    lateinit var userListInteractor: UserListInteractor

    @Mock
    lateinit var mockUserRepo: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userListInteractor = UserListInteractor(mockUserRepo, TestSchedulerProvider())
    }

    @Test
    fun `loadUsers success`() {
        val responseList = listOf<BehanceUser>()
        val expectedList = responseList.map { it.toModel() }

        Mockito.`when`(
                mockUserRepo.loadUsers(anyMap())
        ).thenReturn(
                Single.just(expectedList)
        )

        userListInteractor.loadUsers()
                .test()
                .assertNoErrors()
                .assertValue(expectedList.map { UserGridItem(UserGridItem.TYPE_USER, it) })
                .dispose()
    }

    @Test
    fun `loadUsers errors`() {

        val error = TimeoutException()

        Mockito.`when`(
                mockUserRepo.loadUsers(anyMap())
        ).thenReturn(
                Single.error(error)
        )

        userListInteractor.loadUsers()
                .test()
                .assertError(error)
                .dispose()
    }

}