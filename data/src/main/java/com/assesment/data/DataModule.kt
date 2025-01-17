package com.assesment.data

import com.assesment.data.mapper.GithubUserDetailDomainMapper
import com.assesment.data.mapper.GithubUserDomainMapper
import com.assesment.data.repository.GithubUserRepositoryImpl
import com.assesment.data.repository.HistoryGithubUserRepositoryImpl
import com.assesment.domain.repository.GtihubUserRepository
import com.assesment.domain.repository.HistoryGithubUserRepository
import org.koin.dsl.module

val dataModule = module {
    factory { GithubUserDomainMapper() }
    factory { GithubUserDetailDomainMapper() }

    factory<GtihubUserRepository> {
        GithubUserRepositoryImpl(
            get(),
            get(),
            get(),
            get(),
        )
    }

    factory<HistoryGithubUserRepository> {
        HistoryGithubUserRepositoryImpl(
            get(),
            get(),
            get(),
        )
    }
}