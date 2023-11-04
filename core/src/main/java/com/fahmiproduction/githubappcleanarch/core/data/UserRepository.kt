package com.fahmiproduction.githubappcleanarch.core.data

import com.fahmiproduction.githubappcleanarch.core.data.source.local.LocalDataSource
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.RemoteDataSource
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.network.ApiResponse
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.response.UserResponse
import com.fahmiproduction.githubappcleanarch.core.domain.model.User
import com.fahmiproduction.githubappcleanarch.core.domain.repository.IUserRepository
import com.fahmiproduction.githubappcleanarch.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IUserRepository {

    override fun getAllUser(): Flow<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserResponse>>() {
            override fun loadFromNetwork(data: List<UserResponse>): Flow<List<User>> {
                return DataMapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> {
                return remoteDataSource.getAllUser()
            }
        }.asFlow()

    override fun getDetailUser(username: String): Flow<Resource<User>> =
        object : NetworkBoundResource<User, UserResponse>() {
            override fun loadFromNetwork(data: UserResponse): Flow<User> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<UserResponse>> {
                return remoteDataSource.getDetailUser(username)
            }
        }.asFlow()


    override fun getFavoriteUser(): Flow<List<User>> {
        return localDataSource.getFavoriteUser().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteState(username: String): Flow<User>? {
        return localDataSource.getFavoriteState(username)?.map {
            DataMapper.mapEntityToDomain(it)
        }
    }

    override suspend fun insertFavoriteUser(user: User) {
        val userEntity = DataMapper.mapDomainToEntity(user)
        return localDataSource.insertFavoriteUser(userEntity)
    }

    override suspend fun deleteFavoriteUser(user: User): Int {
        val userEntity = DataMapper.mapDomainToEntity(user)
        return localDataSource.deleteFavoriteUser(userEntity)
    }

}

