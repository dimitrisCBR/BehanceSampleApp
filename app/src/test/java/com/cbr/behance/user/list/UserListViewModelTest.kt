package com.cbr.behance.user.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cbr.behance.user.list.recycler.UserGridItem
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UserListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var userListVM: UserListViewModel

    @Mock
    lateinit var mockInteractor: UserListInteractor

    @Mock
    lateinit var mockStateObserver: Observer<UsersUI>
    @Mock
    lateinit var mockListObserver: Observer<List<UserGridItem>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userListVM = UserListViewModel(mockInteractor)
        userListVM.userListItems().observeForever(mockListObserver)
        userListVM.usersUI().observeForever(mockStateObserver)
    }

    @Test
    fun `loadUsers success`() {
        val users = listOf<UserGridItem>()
        Mockito.`when`(mockInteractor.loadUsers())
                .thenReturn(Single.just(users))

        userListVM.loadUsers()
        assertNotNull(userListVM.usersUI().value)
        assertEquals(userListVM.usersUI().value, Success)
    }


    @Test
    fun `loadUsers error`() {
        val error = Throwable("Something went wrong")

        Mockito.`when`(mockInteractor.loadUsers())
                .thenReturn(Single.error(error))

        userListVM.loadUsers()
        assertNotNull(userListVM.usersUI().value)
        assertEquals(userListVM.usersUI().value, Error(error.message ?: ""))
    }

    @Test
    fun `handleNewUsers behavior`() {

        val oldItems = listOf(
                UserGridItem(UserGridItem.TYPE_USER, 1),
                UserGridItem(UserGridItem.TYPE_USER, 2),
                UserGridItem(UserGridItem.TYPE_USER, 3)
        )

        Mockito.`when`(mockInteractor.loadUsers())
                .thenReturn(Single.just(oldItems.toMutableList()))

        val subject = Mockito.spy(userListVM)
        subject.loadUsers()

        assertTrue(subject.userListItems().value?.last()?.isLoading() ?: false)
        assertTrue(subject.userListItems().value?.size ?: 0 == (oldItems.size + 1))


    }
}