package com.fahmiproduction.githubappcleanarch.core.data.source.local

import com.fahmiproduction.githubappcleanarch.core.data.source.local.entity.UserEntity
import com.fahmiproduction.githubappcleanarch.core.data.source.local.room.UserDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val userDao: UserDao) {

    fun getFavoriteUser(): Flow<List<UserEntity>> = userDao.getFavoriteUser()

    fun getFavoriteState(username: String): Flow<UserEntity>? =
        userDao.getFavoriteState(username)

    suspend fun insertFavoriteUser(user: UserEntity) = userDao.insertFavoriteUser(user)

    suspend fun deleteFavoriteUser(user: UserEntity) = userDao.deleteFavoriteUser(user)

}