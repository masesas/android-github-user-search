package com.assesment.remote.datasource

import com.assesment.data.datasource.remote.GithubUserRemoteDataSource
import com.assesment.data.model.GithubUserDetailModel
import com.assesment.data.model.GithubUserModel
import com.assesment.remote.api.GithubUserApiService
import com.assesment.remote.mapper.RemoteGithubUserDataMapper
import com.assesment.remote.mapper.RemoteGithubUserDetailDataMapper

class GithubUserRemoteDataSourceImpl(
    private val githubUserApiService: GithubUserApiService,
    private val remoteGithubUserDataMapper: RemoteGithubUserDataMapper,
    private val remoteGithubUserDetailDataMapper: RemoteGithubUserDetailDataMapper
) : GithubUserRemoteDataSource {
    override suspend fun getSearchUser(
        query: String,
        page: Int,
        perPage: Int
    ): List<GithubUserModel> {
        val request = githubUserApiService.getSearchUsers(query, page, perPage)
        return remoteGithubUserDataMapper.fromList(request.items)
    }

    override suspend fun getUserDetail(username: String): GithubUserDetailModel =
        remoteGithubUserDetailDataMapper.from(githubUserApiService.getUserDetail(username))
}