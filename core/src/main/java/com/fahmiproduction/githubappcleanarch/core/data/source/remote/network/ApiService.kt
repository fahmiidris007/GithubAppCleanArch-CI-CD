package com.fahmiproduction.githubappcleanarch.core.data.source.remote.network

import com.fahmiproduction.githubappcleanarch.core.data.source.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getList(@Query("page") page: String): List<UserResponse>

    @GET("users/{username}")
    suspend fun getDetail(@Path("username") username: String): UserResponse

}