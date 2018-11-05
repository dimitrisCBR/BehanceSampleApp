package com.futureworkshops.data.model.domain

/**
 * Created by dimitrios on 03/11/2018.
 */

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
        val modules: List<ProjectModule>,
        val copyright: String,
        val featuredOn: FeaturedOn
)