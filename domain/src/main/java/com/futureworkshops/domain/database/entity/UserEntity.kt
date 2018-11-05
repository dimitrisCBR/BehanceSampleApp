package com.futureworkshops.domain.database.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.futureworkshops.data.model.domain.Stats
import com.futureworkshops.data.model.domain.User

/**
 * Created by dimitrios on 03/11/2018.
 */
@Entity
data class UserEntity constructor(
        @PrimaryKey
        var id: Long,
        var firstname: String,
        var lastname: String,
        var username: String,
        var city: String,
        var state: String,
        var country: String,
        var company: String,
        var occupation: String,
        var createdon: Long,
        var url: String,
        var images: Map<String, String>,
        var displayname: String,
        var fields: List<String>,
        @Embedded
        var stats: Stats
) {

    @Ignore
    constructor() : this(0, "", "", "", "", "", "",
            "", "", 0, "", mapOf<String, String>(), "",
            listOf<String>(), Stats(0, 0, 0, 0, 0))

    fun toModel(): User = User(id, firstname, lastname, username, city, state, country,
            company, occupation, createdon, url, images, displayname, fields, stats)

    companion object {

        fun fromModel(user: User) = UserEntity(
                user.id,
                user.firstName,
                user.lastName,
                user.username,
                user.city,
                user.state,
                user.country,
                user.company,
                user.occupation,
                user.createdOn,
                user.url,
                user.images,
                user.displayName,
                user.fields,
                user.stats)
    }
}
