package com.assesment.domain.usecase

import com.assesment.domain.model.GithubUser
import com.assesment.domain.repository.HistoryGithubUserRepository
import com.assesment.shared.utils.Resource

data class SaveSearchGithubUserParams(
    val query: String,
    val users: List<GithubUser>
)

class SaveSearchGithubUserUseCase(
    private val repository: HistoryGithubUserRepository
) {
    suspend fun run(params: SaveSearchGithubUserParams): Resource<Boolean> {
        return repository.saveSearchUser(params.query, params.users)
    }
}