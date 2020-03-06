package com.samiu.host.base

import com.samiu.host.model.http.WanClient
import com.samiu.host.model.repository.wan.WanHomeRepository
import com.samiu.host.model.repository.wan.WanProjectRepository
import com.samiu.host.model.repository.wan.WanSquareRepository
import com.samiu.host.model.repository.wan.WanSystemRepository
import com.samiu.host.viewmodel.wan.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Samiu 2020/3/3
 */

val wanViewModelModule = module {
    viewModel { WanHomeViewModel(get()) }
    viewModel { WanSquareViewModel(get()) }
    viewModel { WanProjectViewModel(get()) }
    viewModel { WanSystemViewModel(get()) }
    viewModel { RecyclerViewModel(get()) }
}

val wanRepositoryModule = module {
    single { WanClient.service }
    single { CoroutineDispatcherProvider() }
    single { WanHomeRepository() }
    single { WanSquareRepository() }
    single { WanProjectRepository() }
    single { WanSystemRepository() }
}

val wanModule = listOf(
    wanViewModelModule,
    wanRepositoryModule
)