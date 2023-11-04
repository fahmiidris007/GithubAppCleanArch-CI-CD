package com.fahmiproduction.githubappcleanarch.core.domain.usecase

import com.fahmiproduction.githubappcleanarch.core.data.Resource
import com.fahmiproduction.githubappcleanarch.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getAllUser(): Flow<Resource<List<User>>>

    fun getDetailUser(username: String): Flow<Resource<User>>

    fun getFavoriteUser(): Flow<List<User>>

    fun getFavoriteState(username: String): Flow<User>?
    suspend fun insertFavoriteUser(user: User)

    suspend fun deleteFavoriteUser(user: User): Int
}