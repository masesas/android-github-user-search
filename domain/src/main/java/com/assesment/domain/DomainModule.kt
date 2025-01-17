package com.assesment.domain

import com.assesment.domain.usecase.GetGithubUserDetailUseCase
import com.assesment.domain.usecase.GetHistorySearchQuery
import com.assesment.domain.usecase.GetHistorySearchUserUseCase
import com.assesment.domain.usecase.GetSearchGithubUserUseCase
import com.assesment.domain.usecase.SaveGithubUserDetailUseCase
import com.assesment.domain.usecase.SaveSearchGithubUserUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetSearchGithubUserUseCase(get()) }
    factory { GetGithubUserDetailUseCase(get()) }
    factory { GetHistorySearchQuery(get()) }
    factory { GetHistorySearchUserUseCase(get()) }
    factory { SaveSearchGithubUserUseCase(get()) }
    factory { SaveGithubUserDetailUseCase(get()) }
}