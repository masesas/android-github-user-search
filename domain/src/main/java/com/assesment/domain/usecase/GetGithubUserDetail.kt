package com.assesment.domain.usecase

import com.assesment.domain.model.GithubUserDetail
import com.assesment.domain.repository.GtihubUserRepository
import com.assesment.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

data class GetGithubUserDetailParams(val username: String)

class GetGithubUserDetail(
    private val repository: GtihubUserRepository
) : UseCaseWithParams<GetGithubUserDetailParams, GithubUserDetail>() {

    override suspend fun run(params: GetGithubUserDetailParams): Flow<Resource<GithubUserDetail>> {
        return repository.getUserDetail(params.username)
    }

}