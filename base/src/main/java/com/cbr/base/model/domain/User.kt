package com.cbr.base.model.domain

import com.cbr.base.model.commons.Stats

data class User(
        val id: Long,
        val firstName: String,
        val lastName: String,
        val username: String,
        val city: String,
        val state: String,
        val country: String,
        val company: String,
        val occupation: String,
        val createdOn: Long,
        val url: String,
        val images: Map<String, String>,
        val displayName: String,
        val fields: List<String>,
        val stats: Stats
)

