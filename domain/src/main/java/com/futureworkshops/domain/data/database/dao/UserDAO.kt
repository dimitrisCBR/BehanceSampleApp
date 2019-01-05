package com.futureworkshops.domain.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.futureworkshops.domain.data.database.entity.UserEntity
import io.reactivex.Flowable


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