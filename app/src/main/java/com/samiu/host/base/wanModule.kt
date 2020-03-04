package com.samiu.host.base

import com.samiu.host.model.http.WanClient
import com.samiu.host.model.repository.wan.HomeRepository
import com.samiu.host.ui.viewmodel.wan.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Samiu 2020/3/3
 */

val wanViewModelModule = module {
    viewModel { HomeViewModel(get()) }
}

val wanRepositoryModule = module {
    single { WanClient.service }
    single { CoroutineDispatcherProvider() }
    single { HomeRepository() }
}

val wanModule = listOf(
    wanViewModelModule,
    wanRepositoryModule
)