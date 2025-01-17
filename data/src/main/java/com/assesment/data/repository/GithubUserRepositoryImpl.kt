package com.assesment.data.repository

import com.assesment.data.datasource.local.GithubUserLocalDataSource
import com.assesment.data.datasource.remote.GithubUserRemoteDataSource
import com.assesment.data.mapper.GithubUserDetailDomainMapper
import com.assesment.data.mapper.GithubUserDomainMapper
import com.assesment.domain.model.GithubUser
import com.assesment.domain.model.GithubUserDetail
import com.assesment.domain.repository.GtihubUserRepository
import com.assesment.shared.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GithubUserRepositoryImpl(
    private val localDataSource: GithubUserLocalDataSource,
    private val remoteDataSource: GithubUserRemoteDataSource,
    private val userListMapper: GithubUserDomainMapper,
    private val userDetailMapper: GithubUserDetailDomainMapper
) : GtihubUserRepository {
    override suspend fun getSearchUser(
        query: String,
        page: Int,
        perPage: Int
    ): Flow<Resource<List<GithubUser>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserDetail(username: String): Flow<Resource<GithubUserDetail>> {
        TODO("Not yet implemented")
    }
}