package com.cbr.base.model.domain

import com.cbr.base.model.api.BehanceModule
import com.cbr.base.model.commons.ProjectCopyright
import com.cbr.base.model.commons.ProjectFeature
import com.cbr.base.model.commons.Stats
import java.util.*

data class Project(
        val id: Long,
        val name: String,
        val publishDate: Date,
        val creationDate: Date,
        val lastModifiedDate: Date,
        val url: String,
        val field: List<String>,
        val covers: HashMap<String, String>,
        val nsfw: Int,
        val owners: List<User>,
        val stats: Stats,
        val description: String,
        val copyright: ProjectCopyright?,
        val projectFeatures: List<ProjectFeature>,
        val projectModules: List<BehanceModule>
) {

    fun getCoverImage() = covers.takeIf { it.isNotEmpty() }?.values?.first() ?: ""
}