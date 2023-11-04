package com.fahmiproduction.githubappcleanarch.core.domain.usecase

import com.fahmiproduction.githubappcleanarch.core.domain.model.User
import com.fahmiproduction.githubappcleanarch.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserInteractor(private val userRepository: IUserRepository) : UserUseCase {

    override fun getAllUser() = userRepository.getAllUser()

    override fun getDetailUser(username: String) = userRepository.getDetailUser(username)

    override fun getFavoriteUser() = userRepository.getFavoriteUser()
    override fun getFavoriteState(username: String): Flow<User>? =
        userRepository.getFavoriteState(username)

    override suspend fun insertFavoriteUser(user: User) = userRepository.insertFavoriteUser(user)

    override suspend fun deleteFavoriteUser(user: User) = userRepository.deleteFavoriteUser(user)
}