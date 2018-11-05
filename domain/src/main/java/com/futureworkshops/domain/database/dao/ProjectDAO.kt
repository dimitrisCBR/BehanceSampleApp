package com.futureworkshops.domain.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.futureworkshops.domain.database.entity.ProjectEntity

/**
 * Created by dimitrios on 12/10/2018.
 */
@Dao
interface ProjectDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(project: ProjectEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(projects: List<ProjectEntity>)

    @Query("SELECT * FROM ProjectEntity")
    fun getAll(): LiveData<List<ProjectEntity>>

    @Query("SELECT * FROM ProjectEntity WHERE id = :projectId ")
    fun findProjectById(projectId: Long): LiveData<ProjectEntity>
}