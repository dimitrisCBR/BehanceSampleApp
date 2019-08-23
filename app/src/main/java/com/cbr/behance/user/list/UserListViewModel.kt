package com.cbr.behance.user.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cbr.base.ui.BaseViewModel
import com.cbr.behance.commons.recycler.LoadingViewHolder
import com.cbr.behance.user.list.recycler.UserGridItem
import timber.log.Timber
import javax.inject.Inject

class UserListViewModel @Inject constructor(
        private val userListInteractor: UserListInteractor
) : BaseViewModel() {

    private val userListStateLiveData = MutableLiveData<UsersUI>()
    private val userItemsLiveData = MutableLiveData<List<UserGridItem>>()

    fun loadUsers() {
        if (userListStateLiveData.value == Loading) {
            return
        }
        userListStateLiveData.postValue(Loading)
        compositeDisposable.add(
                userListInteractor.loadUsers()
                        .subscribe(
                                { userGridItems ->
                                    handleNewGridItems(userGridItems)
                                    userListStateLiveData.postValue(Success)
                                },
                                { t ->
                                    handleError(t)
                                    userListStateLiveData.postValue(Error(t.message ?: ""))
                                }
                        )
        )
    }

    private fun handleError(t: Throwable) {
        Timber.e(t)
    }


    private fun handleNewGridItems(userGridItems: List<UserGridItem>) {
        val items = mutableListOf<UserGridItem>()
        val oldItems = userItemsLiveData.value.orEmpty()

        items.addAll(oldItems.filter { item -> item.itemType != LoadingViewHolder.TYPE_LOADING })
        items.addAll(userGridItems)
        items.add(UserGridItem(LoadingViewHolder.TYPE_LOADING, Unit))

        userItemsLiveData.postValue(items)
    }

    fun userListItems(): LiveData<List<UserGridItem>> = userItemsLiveData

    fun usersUI(): LiveData<UsersUI> = userListStateLiveData

    fun refreshUsers() {
        userListInteractor.refresh()
        loadUsers()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

sealed class UsersUI
object Loading : UsersUI()
data class Error(val message: String) : UsersUI()
object Success : UsersUI()


@Suppress("Unchecked_cast")
class UserListViewModelFactory @Inject constructor(
        private val interactor: UserListInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserListViewModel(interactor) as T
    }

}