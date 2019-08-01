package com.cbr.base.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cbr.base.data.database.entity.UserEntity
import io.reactivex.Single


@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserEntity>)

    @Query("SELECT * FROM UserEntity")
    fun getAll(): Single<List<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE id = :userId ")
    fun findUserById(userId: Long): Single<UserEntity>
}