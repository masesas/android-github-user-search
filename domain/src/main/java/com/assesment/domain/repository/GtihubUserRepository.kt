package com.assesment.domain.repository

import com.assesment.domain.model.GithubUser
import com.assesment.domain.model.GithubUserDetail
import com.assesment.shared.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GtihubUserRepository {
    suspend fun getSearchUser(
        query: String,
        page: Int,
        perPage: Int
    ): Flow<Resource<List<GithubUser>>>

    suspend fun getUserDetail(username: String): Flow<Resource<GithubUserDetail>>
}