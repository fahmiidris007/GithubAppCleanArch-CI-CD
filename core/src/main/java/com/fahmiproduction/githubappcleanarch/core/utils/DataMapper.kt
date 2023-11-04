package com.fahmiproduction.githubappcleanarch.core.utils

import com.fahmiproduction.githubappcleanarch.core.data.source.local.entity.UserEntity
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.response.UserResponse
import com.fahmiproduction.githubappcleanarch.core.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


object DataMapper {
    fun mapResponsesToDomain(input: List<UserResponse>): Flow<List<User>> {
        val userList = ArrayList<User>()
        input.map {
            val user = User(
                login = it.login,
                type = it.type,
                avatarUrl = it.avatarUrl,
                company = it.company,
                publicRepos = it.publicRepos,
                followers = it.followers,
                following = it.following,
                name = it.name,
                location = it.location,
                isFavorite = false
            )
            userList.add(user)
        }
        return flowOf(userList)
    }

    fun mapResponseToDomain(input: UserResponse): Flow<User> {
        val user = User(
            login = input.login,
            type = input.type,
            avatarUrl = input.avatarUrl,
            company = input.company,
            publicRepos = input.publicRepos,
            followers = input.followers,
            following = input.following,
            name = input.name,
            location = input.location,
            isFavorite = false
        )
        return flowOf(user)
    }

    fun mapEntitiesToDomain(input: List<UserEntity>): List<User> =
        input.map {
            User(
                login = it.login,
                type = it.type,
                avatarUrl = it.avatarUrl,
                company = it.company,
                publicRepos = it.publicRepos,
                followers = it.followers,
                following = it.following,
                name = it.name,
                location = it.location,
                isFavorite = it.isFavorite
            )
        }

    fun mapEntityToDomain(input: UserEntity?): User =
        User(
            login = input?.login.toString(),
            type = input?.type,
            avatarUrl = input?.avatarUrl,
            company = input?.company,
            publicRepos = input?.publicRepos,
            followers = input?.followers,
            following = input?.following,
            name = input?.name,
            location = input?.location,
            isFavorite = input?.isFavorite
        )


    fun mapDomainToEntity(input: User) = UserEntity(
        login = input.login,
        type = input.type,
        avatarUrl = input.avatarUrl,
        company = input.company,
        publicRepos = input.publicRepos,
        followers = input.followers,
        following = input.following,
        name = input.name,
        location = input.location,
        isFavorite = input.isFavorite
    )

}