package com.fahmiproduction.githubappcleanarch.core.data.source.remote

import android.util.Log
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.network.ApiResponse
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.network.ApiService
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllUser(): Flow<ApiResponse<List<UserResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getList("1")
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailUser(username: String): Flow<ApiResponse<UserResponse>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getDetail(username)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}
