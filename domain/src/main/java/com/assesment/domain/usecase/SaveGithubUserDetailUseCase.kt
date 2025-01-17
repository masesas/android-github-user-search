package com.assesment.domain.usecase

import com.assesment.domain.model.GithubUserDetail
import com.assesment.domain.repository.HistoryGithubUserRepository
import com.assesment.shared.utils.Resource

data class SaveGithubUserDetailParams(
    val user: GithubUserDetail
)

class SaveGithubUserDetailUseCase(
    private val repository: HistoryGithubUserRepository
) {
    suspend fun run(params: SaveGithubUserDetailParams): Resource<Boolean> {
        return repository.saveUserDetail(params.user)
    }
}