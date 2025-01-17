package com.assesment.domain.usecase

import com.assesment.domain.model.GithubUserDetail
import com.assesment.domain.repository.HistoryGithubUserRepository
import com.assesment.shared.utils.Resource
import kotlinx.coroutines.flow.Flow

data class SaveGithubUserDetailParams(
    val user: GithubUserDetail
)

class SaveGithubUserDetailUseCase(
    private val repository: HistoryGithubUserRepository
) : UseCaseWithParams<SaveGithubUserDetailParams, Boolean>() {
    override suspend fun run(params: SaveGithubUserDetailParams): Flow<Resource<Boolean>> {
        return repository.saveUserDetail(params.user)
    }
}