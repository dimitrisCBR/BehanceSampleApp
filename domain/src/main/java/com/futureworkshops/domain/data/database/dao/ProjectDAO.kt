package com.futureworkshops.domain.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.futureworkshops.domain.data.database.entity.ProjectEntity
import io.reactivex.Flowable


@Dao
interface ProjectDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(project: ProjectEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(projects: List<ProjectEntity>)

    @Query("SELECT * FROM ProjectEntity")
    fun getAll(): Flowable<List<ProjectEntity>>

    @Query("SELECT * FROM ProjectEntity WHERE id = :projectId ")
    fun findProjectById(projectId: Long): Flowable<ProjectEntity>
}