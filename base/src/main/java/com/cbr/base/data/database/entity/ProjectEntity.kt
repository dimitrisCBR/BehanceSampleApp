package com.cbr.base.data.database.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.cbr.base.model.api.BehanceModule
import com.cbr.base.model.commons.ProjectCopyright
import com.cbr.base.model.commons.ProjectFeature
import com.cbr.base.model.commons.Stats
import com.cbr.base.model.domain.Project
import com.cbr.base.model.domain.User
import java.util.*
import kotlin.collections.HashMap

@Entity
data class ProjectEntity
constructor(
        @PrimaryKey
        var id: Long = 0,
        var name: String = "",
        var publish_date: Date = Date(),
        var creation_date: Date = Date(),
        var last_modified_date: Date = Date(),
        var url: String = "",
        var field: List<String> = listOf(),
        var covers: HashMap<String, String> = hashMapOf(),
        var nsfw: Int = 0,
        @Ignore
        var owners: List<User> = listOf(),
        @Ignore
        var stats: Stats = Stats(0, 0, 0),
        var description: String = "",
        @Ignore
        var copyright: ProjectCopyright? = null,
        @Ignore
        var projectFeatures: List<ProjectFeature> = mutableListOf(),
        @Ignore
        var projectModules: List<BehanceModule> = mutableListOf()
) {

    fun toModel(): Project = Project(
            id, name, publish_date, creation_date, last_modified_date, url, field, covers,
            nsfw, owners, stats, description, copyright, projectFeatures, projectModules)

    companion object {

        fun fromModel(project: Project) = ProjectEntity(
                project.id,
                project.name,
                project.publishDate,
                project.creationDate,
                project.lastModifiedDate,
                project.url,
                project.field,
                project.covers,
                project.nsfw,
                project.owners,
                project.stats,
                project.description,
                project.copyright,
                project.projectFeatures,
                project.projectModules)
    }
}