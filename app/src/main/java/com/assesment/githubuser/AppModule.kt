package com.assesment.githubuser

import com.assesment.data.dataModule
import com.assesment.domain.domainModule
import com.assesment.githubuser.ui.detail.SearchDetailViewModel
import com.assesment.githubuser.ui.search.SearchViewModel
import com.assesment.githubuser.worker.CachedDetailWorker
import com.assesment.githubuser.worker.CachedSearchWorker
import com.assesment.local.localModule
import com.assesment.remote.remoteModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val workerModule = module {
    worker { CachedSearchWorker(get(), get(), get()) }
    worker { CachedDetailWorker(get(), get(), get()) }
}

val uiModule = module {
    viewModel { SearchViewModel(get(), get(), get()) }
    viewModel { SearchDetailViewModel(get()) }
}

val appModule = listOf(
    remoteModule,
    localModule,
    dataModule,
    domainModule,
    workerModule,
    uiModule,
)