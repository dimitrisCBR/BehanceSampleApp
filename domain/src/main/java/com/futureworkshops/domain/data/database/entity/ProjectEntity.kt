package com.futureworkshops.domain.data.database.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.futureworkshops.data.model.commons.ProjectFeature
import com.futureworkshops.data.model.commons.Stats
import com.futureworkshops.data.model.domain.*

@Entity
data class ProjectEntity
constructor(
        @PrimaryKey
        var id: Long = 0,
        var name: String = "",
        var publish_date: Long = 0,
        var creation_date: Long = 0,
        var last_modified_date: Long = 0,
        var url: String = "",
        var field: List<String> = listOf(),
        var covers: HashMap<String, String> = hashMapOf(),
        var nsfw: Int = 0,
        @Ignore
        var owners: List<User> = listOf(),
        @Ignore
        var stats: Stats = Stats(0,0,0),
        var description: String = "",
        var copyright: String = "",
        @Ignore
        var projectFeature: List<ProjectFeature> = mutableListOf()
) {

    fun toModel(): Project = Project(
            id, name, publish_date, creation_date, last_modified_date, url, field, covers,
            nsfw, owners, stats, description, copyright, projectFeature)

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
                project.projectFeature)
    }
}