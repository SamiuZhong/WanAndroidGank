package com.samiu.host.base

import com.samiu.host.model.http.WanClient
import com.samiu.host.model.repository.*
import com.samiu.host.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Samiu 2020/3/3
 */

val viewModelModule = module {
    viewModel { WanHomeViewModel(get()) }
    viewModel { WanSquareViewModel(get()) }
    viewModel { WanProjectViewModel(get()) }
    viewModel { WanSystemViewModel(get()) }
    viewModel { WanWxViewModel(get()) }
    viewModel { WanSearchViewModel(get()) }
    viewModel { RecyclerViewModel(get()) }
}

val repositoryModule = module {
    single { WanClient.service }
    single { CoroutineDispatcherProvider() }
    single { WanHomeRepository() }
    single { WanSquareRepository() }
    single { WanProjectRepository() }
    single { WanSystemRepository() }
    single { WanWxRepository() }
    single { WanSearchRepository() }
}

val module = listOf(
    viewModelModule,
    repositoryModule
)