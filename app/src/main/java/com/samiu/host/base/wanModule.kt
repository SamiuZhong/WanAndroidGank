package com.samiu.host.base

import com.samiu.host.model.http.WanClient
import com.samiu.host.model.repository.wan.HomeRepository
import com.samiu.host.model.repository.wan.ProjectRepository
import com.samiu.host.model.repository.wan.SquareRepository
import com.samiu.host.viewmodel.wan.HomeViewModel
import com.samiu.host.viewmodel.wan.ProjectViewModel
import com.samiu.host.viewmodel.wan.SquareViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Samiu 2020/3/3
 */

val wanViewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SquareViewModel(get()) }
    viewModel { ProjectViewModel(get()) }
}

val wanRepositoryModule = module {
    single { WanClient.service }
    single { CoroutineDispatcherProvider() }
    single { HomeRepository() }
    single { SquareRepository() }
    single { ProjectRepository() }
}

val wanModule = listOf(
    wanViewModelModule,
    wanRepositoryModule
)