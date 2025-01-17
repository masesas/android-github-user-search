package com.assesment.data.repository

import com.assesment.data.datasource.local.GithubUserLocalDataSource
import com.assesment.data.mapper.GithubUserDetailDomainMapper
import com.assesment.data.mapper.GithubUserDomainMapper
import com.assesment.domain.model.GithubUser
import com.assesment.domain.model.GithubUserDetail
import com.assesment.domain.repository.HistoryGithubUserRepository
import com.assesment.shared.utils.Resource
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
    ): Flow<Resource<Boolean>> = flow {
        try {
            localDataSource.cachedUsers(query, userListMapper.toList(users))
            emit(Resource.Success(true))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Success(false))
        }
    }

    override suspend fun saveUserDetail(
        user: GithubUserDetail
    ): Flow<Resource<Boolean>> = flow {
        try {
            localDataSource.cachedUserDetail(user.username, userDetailMapper.to(user))
            emit(Resource.Success(true))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Success(false))
        }
    }

    override suspend fun getHistorySearchQuery(): Flow<Resource<List<String>>> = flow {
        try {
            val result = localDataSource.getCachedQueries()
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }

    override suspend fun getHistorySearchUser(
        query: String,
        page: Int,
        perPage: Int
    ): Flow<Resource<List<GithubUser>>> = flow {
        try {
            val result = localDataSource.getCachedSearchUsers(query, page, perPage)
            emit(Resource.Success(userListMapper.fromList(result)))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e))
        }
    }
}