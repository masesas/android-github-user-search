package com.assesment.domain.usecase

import com.assesment.domain.model.GithubUser
import com.assesment.domain.repository.GtihubUserRepository
import com.assesment.shared.utils.Resource
import kotlinx.coroutines.flow.Flow

data class GetSearchGithubUserParams(val query: String, val page: Int = 1, val perPage: Int = 20)

class GetSearchGithubUser(
    private val repository: GtihubUserRepository
) : UseCaseWithParams<GetSearchGithubUserParams, List<GithubUser>>() {

    override suspend fun run(params: GetSearchGithubUserParams): Flow<Resource<List<GithubUser>>> {
        return repository.getSearchUser(params.query, params.page, params.perPage)
    }

}