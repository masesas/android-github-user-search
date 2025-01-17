package com.assesment.data.datasource.local

import com.assesment.data.model.GithubUserDetailModel
import com.assesment.data.model.GithubUserModel

interface GithubUserLocalDataSource {
    suspend fun cachedQuery(query: String)
    suspend fun cachedUsers(query: String, users: List<GithubUserModel>)
    suspend fun cachedUserDetail(username: String, user: GithubUserDetailModel)
    suspend fun getCachedQueries(): List<String>
    suspend fun getCachedSearchUsers(query: String?, page: Int, perPage: Int): List<GithubUserModel>
    suspend fun getCachedUserDetail(username: String): GithubUserDetailModel?
}