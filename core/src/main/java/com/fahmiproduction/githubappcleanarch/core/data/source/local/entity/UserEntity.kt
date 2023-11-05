package com.fahmiproduction.githubappcleanarch.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "login")
    var login: String,

    @ColumnInfo(name = "type")
    var type: String?,

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String?,

    @ColumnInfo(name = "company")
    var company: String?,

    @ColumnInfo(name = "publicRepos")
    var publicRepos: Int?,

    @ColumnInfo(name = "followers")
    var followers: Int?,

    @ColumnInfo(name = "following")
    var following: Int?,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "location")
    var location: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean?,
)
