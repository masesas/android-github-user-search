package com.assesment.remote

import com.assesment.data.datasource.remote.GithubUserRemoteDataSource
import com.assesment.remote.api.ApiConfig
import com.assesment.remote.datasource.GithubUserRemoteDataSourceImpl
import com.assesment.remote.mapper.RemoteGithubUserDataMapper
import com.assesment.remote.mapper.RemoteGithubUserDetailDataMapper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val remoteModule = module {
    single { ApiConfig.build(get()) }
    single { ApiConfig.provideOkHttpClient(get(), get()) }
    single { ApiConfig.provideLoggingInterceptor() }
    single { ApiConfig.provideChuckerInterceptor(androidContext()) }

    factory { ApiConfig.provideGithubUserApi(get()) }
    factory { RemoteGithubUserDataMapper() }
    factory { RemoteGithubUserDetailDataMapper() }
    factory<GithubUserRemoteDataSource> { GithubUserRemoteDataSourceImpl(get(), get(), get()) }
}