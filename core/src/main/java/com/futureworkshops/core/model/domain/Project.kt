package com.futureworkshops.core.model.domain

import com.futureworkshops.core.model.commons.ProjectFeature
import com.futureworkshops.core.model.commons.Stats

data class Project(
        val id: Long,
        val name: String,
        val publishDate: Long,
        val creationDate: Long,
        val lastModifiedDate: Long,
        val url: String,
        val field: List<String>,
        val covers: HashMap<String, String>,
        val nsfw: Int,
        val owners: List<User>,
        val stats: Stats,
        val description: String,
        val copyright: String,
        val projectFeature: List<ProjectFeature>
)