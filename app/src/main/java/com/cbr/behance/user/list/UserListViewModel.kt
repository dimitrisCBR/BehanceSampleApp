package com.cbr.behance.user.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.futureworkshops.core.ui.BaseViewModel
import com.futureworkshops.core.ui.Outcome

import com.futureworkshops.core.model.domain.User
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserListViewModel @Inject constructor(
        private val userListInteractor: UserListInteractor
) : BaseViewModel() {

    private val usersLiveData = MutableLiveData<Outcome<List<User>>>()

    init {
        loadUsers()
        benchmark()
    }

    private fun benchmark() {
        Observable.range(0, 1000)
                .flatMap {
                    Single.just(it)
                            .subscribeOn(Schedulers.newThread())
                            .map { value -> value % 5 }
                            .toObservable()
                }
                .subscribe()
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