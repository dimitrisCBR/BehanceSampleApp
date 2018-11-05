package com.futureworkshops.domain.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.futureworkshops.domain.database.entity.UserEntity
import io.reactivex.Flowable

/**
 * Created by dimitrios on 12/10/2018.
 */
@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserEntity>)

    @Query("SELECT * FROM UserEntity")
    fun getAll(): Flowable<List<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE id = :userId ")
    fun findUserById(userId: Long): Flowable<UserEntity>
}