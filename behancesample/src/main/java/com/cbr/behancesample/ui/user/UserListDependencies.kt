package com.cbr.behancesample.ui.user

import com.cbr.behancesample.domain.di.ApplicationComponent
import com.cbr.behancesample.domain.di.scope.ActivityScope
import com.cbr.behancesample.domain.repository.UserRepository
import com.futureworkshops.domain.database.RoomDB
import com.futureworkshops.domain.database.dao.UserDAO
import dagger.Component
import dagger.Module
import dagger.Provides

/**
 * Created by dimitrios on 05/11/2018.
 */
@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [UserListModule::class])
interface UserListComponent {

    fun userDao(): UserDAO

    fun inject(userListActivity: UsersActivity)

}

@Module
class UserListModule {

    @Provides
    @ActivityScope
    fun userDao(roomDB: RoomDB) = roomDB.userDao()

    @Provides
    @ActivityScope
    fun userViewModelFactory(userRepository: UserRepository) = UserListViewModelFactory(userRepository)

}