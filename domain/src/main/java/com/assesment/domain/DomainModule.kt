package com.assesment.domain

import com.assesment.domain.usecase.GetGithubUserDetail
import com.assesment.domain.usecase.GetHistorySearchQuery
import com.assesment.domain.usecase.GetHistorySearchUserUseCase
import com.assesment.domain.usecase.GetSearchGithubUser
import com.assesment.domain.usecase.SaveGithubUserDetailUseCase
import com.assesment.domain.usecase.SaveSearchGithubUserUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetSearchGithubUser(get()) }
    factory { GetGithubUserDetail(get()) }
    factory { GetHistorySearchQuery(get()) }
    factory { GetHistorySearchUserUseCase(get()) }
    factory { SaveSearchGithubUserUseCase(get()) }
    factory { SaveGithubUserDetailUseCase(get()) }
}