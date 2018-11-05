package com.futureworkshops.domain.database.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.futureworkshops.data.model.domain.FeaturedOn
import com.futureworkshops.data.model.domain.ProjectModule
import com.futureworkshops.data.model.domain.Stats
import com.futureworkshops.data.model.domain.User

/**
 * Created by dimitrios on 03/11/2018.
 */
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
        var covers: Map<String, String> = mapOf(),
        var nsfw: Int = 0,
        @Ignore
        var owners: List<User> = listOf(),
        @Ignore
        var stats: Stats = Stats(0,0,0,0,0),
        var description: String = "",
        @Ignore
        var modules: List<ProjectModule> = listOf(),
        var copyright: String = "",
        @Ignore
        var featured_on: FeaturedOn = FeaturedOn("",0)
) {

//    @Ignore
//    constructor() : this(0, "", 0, 0, 0, "",
//            listOf<String>(), mapOf(), 0, listOf(), Stats(0, 0, 0,
//            0, 0), "", listOf(), "", FeaturedOn("", 0))
}