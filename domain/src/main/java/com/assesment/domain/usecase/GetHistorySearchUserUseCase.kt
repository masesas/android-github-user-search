package com.assesment.domain.usecase

import com.assesment.domain.model.GithubUser
import com.assesment.domain.repository.HistoryGithubUserRepository
import com.assesment.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

data class GetHistorySearchUserParams(
    val query: String,
    val page: Int,
    val perPage: Int = 20
)

class GetHistorySearchUserUseCase(
    private val repository: HistoryGithubUserRepository
) : UseCaseWithParams<GetHistorySearchUserParams, List<GithubUser>>() {

    override suspend fun run(params: GetHistorySearchUserParams): Flow<Resource<List<GithubUser>>> {
        return repository.getHistorySearchUser(params.query, params.page, params.perPage)
    }

}