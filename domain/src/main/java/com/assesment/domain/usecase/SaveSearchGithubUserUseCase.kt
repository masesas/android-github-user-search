package com.assesment.domain.usecase

import com.assesment.domain.model.GithubUser
import com.assesment.domain.repository.HistoryGithubUserRepository
import com.assesment.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

data class SaveSearchGithubUserParams(
    val query: String,
    val users: List<GithubUser>
)

class SaveSearchGithubUserUseCase(
    private val repository: HistoryGithubUserRepository
) : UseCaseWithParams<SaveSearchGithubUserParams, Boolean>() {

    override suspend fun run(params: SaveSearchGithubUserParams): Flow<Resource<Boolean>> {
        return repository.saveSearchUser(params.query, params.users)
    }
}