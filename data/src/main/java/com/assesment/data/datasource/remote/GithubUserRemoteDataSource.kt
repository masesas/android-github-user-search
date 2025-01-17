package com.assesment.data.datasource.remote

import com.assesment.data.model.GithubUserDetailModel
import com.assesment.data.model.GithubUserModel

interface GithubUserRemoteDataSource {
    suspend fun getSearchUser(query: String, page: Int, perPage: Int): List<GithubUserModel>
    suspend fun getUserDetail(username: String): GithubUserDetailModel
}