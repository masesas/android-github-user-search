package com.assesment.domain.usecase

import com.assesment.domain.repository.HistoryGithubUserRepository
import com.assesment.shared.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetHistorySearchQuery(
    private val repository: HistoryGithubUserRepository
) : UseCaseWithNoParams<List<String>>() {
    override suspend fun run(): Flow<Resource<List<String>>> {
        return repository.getHistorySearchQuery()
    }
}