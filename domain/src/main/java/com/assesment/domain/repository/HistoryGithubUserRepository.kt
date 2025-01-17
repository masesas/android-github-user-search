package com.assesment.domain.repository

import com.assesment.domain.model.GithubUser
import com.assesment.domain.model.GithubUserDetail
import com.assesment.shared.utils.Resource
import kotlinx.coroutines.flow.Flow

interface HistoryGithubUserRepository {
    suspend fun saveSearchUser(query: String, users: List<GithubUser>): Resource<Boolean>

    suspend fun saveUserDetail(user: GithubUserDetail): Resource<Boolean>

    suspend fun getHistorySearchQuery(): Flow<Resource<List<String>>>

    suspend fun getHistorySearchUser(
        query: String,
        page: Int,
        perPage: Int
    ): Flow<Resource<List<GithubUser>>>
}