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
    ): Flow<Resource<List<GithubUser>>> = flow {
        try {
            val result = remoteDataSource.getSearchUser(query, page, perPage)
            emit(Resource.Success(userListMapper.fromList(result)))
        } catch (e: Exception) {
            e.printStackTrace()
            try {
                val cached = localDataSource.getCachedSearchUsers(query, page, perPage)
                emit(Resource.Success(userListMapper.fromList(cached)))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    override suspend fun getUserDetail(username: String): Flow<Resource<GithubUserDetail>> =
        flow {
            try {
                val result = remoteDataSource.getUserDetail(username)
                emit(Resource.Success(userDetailMapper.from(result)))
            } catch (e: Exception) {
                e.printStackTrace()
                try {
                    val cached = localDataSource.getCachedUserDetail(username)
                    emit(Resource.Success(userDetailMapper.from(cached!!)))
                } catch (e: Exception) {
                    emit(Resource.Error(e))
                }
            }
        }
}