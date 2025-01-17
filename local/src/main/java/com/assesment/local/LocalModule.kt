package com.assesment.local

import com.assesment.data.datasource.local.GithubUserLocalDataSource
import com.assesment.local.datasource.GithubUserLocalDataSourceImpl
import com.assesment.local.db.AppDatabase
import com.assesment.local.mapper.LocalGithubUserDataMapper
import com.assesment.local.mapper.LocalGithubUserDetailDataMapper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single { AppDatabase.getInstance(androidContext()) }
    single { get<AppDatabase>().getGithubUserDao() }
    single { get<AppDatabase>().getGithubUserDetailDao() }
    single { get<AppDatabase>().getQueryDao() }

    factory { LocalGithubUserDataMapper() }
    factory { LocalGithubUserDetailDataMapper() }

    factory<GithubUserLocalDataSource> {
        GithubUserLocalDataSourceImpl(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
}