package com.cbr.behancesample.ui.user

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.futureworkshops.data.model.domain.User
import com.futureworkshops.domain.Outcome
import com.cbr.behancesample.domain.repository.UserRepository
import com.futureworkshops.domain.toLiveData
import com.futureworkshops.domain.network.query.UsersQuery
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by dimitrios on 12/10/2018.
 */

class UserListViewModel @Inject constructor(
        private val usersRepository: UserRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val usersApiQuery = UsersQuery()

    val outcome: LiveData<Outcome<List<User>>> by lazy {
        usersRepository.outcome.toLiveData(compositeDisposable)
    }

    fun getUsers() {
        if (outcome.value == null)
            usersRepository.fetchUsers()
    }

    fun refreshUsers() {
        usersRepository.refreshUsers(usersApiQuery)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}

@Suppress("Unchecked_cast")
class UserListViewModelFactory(private val repo : UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserListViewModel(repo) as T
    }

}