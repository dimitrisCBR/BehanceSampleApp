package com.cbr.behancesample.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cbr.behancesample.common.Outcome
import com.futureworkshops.data.model.domain.User
import com.futureworkshops.domain.data.network.query.UsersQuery
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserListViewModel @Inject constructor(
        private val userListInteractor: UserListInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val usersApiQuery = UsersQuery()

    private val usersLiveData = MutableLiveData<Outcome<List<User>>>()

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