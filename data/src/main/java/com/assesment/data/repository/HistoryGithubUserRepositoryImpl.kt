package com.assesment.data.repository

import com.assesment.data.datasource.local.GithubUserLocalDataSource
import com.assesment.data.mapper.GithubUserDetailDomainMapper
import com.assesment.data.mapper.GithubUserDomainMapper
import com.assesment.domain.model.GithubUser
import com.assesment.domain.model.GithubUserDetail
import com.assesment.domain.repository.HistoryGithubUserRepository
import com.assesment.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HistoryGithubUserRepositoryImpl(
    private val localDataSource: GithubUserLocalDataSource,
    private val userListMapper: GithubUserDomainMapper,
    private val userDetailMapper: GithubUserDetailDomainMapper
) : HistoryGithubUserRepository {
    override suspend fun saveSearchUser(
        query: String,
        users: List<GithubUser>
    ): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUserDetail(user: GithubUserDetail): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun getHistorySearchQuery(): Flow<Resource<List<String>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getHistorySearchUser(
        query: String,
        page: Int,
        perPage: Int
    ): Flow<Resource<List<GithubUser>>> {
        TODO("Not yet implemented")
    }

}