package com.assesment.remote.api

import com.assesment.remote.model.RemoteGithubUserDetail
import com.assesment.remote.model.RemoteGithubUserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubUserApiService {
    @GET("search/users")
    suspend fun getSearchUsers(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): RemoteGithubUserResponse

    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") username: String
    ): RemoteGithubUserDetail

}