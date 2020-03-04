package com.samiu.host.module

import com.samiu.host.CoroutineDispatcherProvider
import com.samiu.host.model.api.WanClient
import com.samiu.host.model.repository.HomeRepository
import com.samiu.host.ui.wan.nav.viewmodel.HomeViewModel
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

val wanModule = listOf(wanViewModelModule, wanRepositoryModule)