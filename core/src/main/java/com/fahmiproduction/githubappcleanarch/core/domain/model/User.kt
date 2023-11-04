package com.fahmiproduction.githubappcleanarch.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val login: String,
    val type: String?,
    val avatarUrl: String?,
    val company: String?,
    val publicRepos: Int?,
    val followers: Int?,
    val following: Int?,
    val name: String?,
    val location: String?,
    var isFavorite: Boolean?
) : Parcelable
