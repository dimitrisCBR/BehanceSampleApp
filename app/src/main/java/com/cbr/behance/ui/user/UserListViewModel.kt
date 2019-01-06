package com.cbr.behance.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cbr.behance.common.BaseViewModel
import com.cbr.behance.common.Outcome
import com.futureworkshops.data.model.domain.User
import javax.inject.Inject

class UserListViewModel @Inject constructor(
        private val userListInteractor: UserListInteractor
) : BaseViewModel() {

    private val usersLiveData = MutableLiveData<Outcome<List<User>>>()

    init {
        loadUsers()
    }

    fun loadUsers() {
        compositeDisposable.add(
                userListInteractor.loadUsers()
                        .subscribe { outcome ->
                            usersLiveData.postValue(outcome)
                        }
        )
    }

    fun userListLiveData(): LiveData<Outcome<List<User>>> = usersLiveData

    fun refreshUsers() {
        userListInteractor.refresh()
        loadUsers()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

@Suppress("Unchecked_cast")
class UserListViewModelFactory @Inject constructor(
        private val interactor: UserListInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserListViewModel(interactor) as T
    }

}