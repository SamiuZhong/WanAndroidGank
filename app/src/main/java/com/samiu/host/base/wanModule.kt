package com.samiu.host.base

import com.samiu.host.model.http.GankClient
import com.samiu.host.model.http.WanClient
import com.samiu.host.model.repository.gank.GankRepository
import com.samiu.host.model.repository.wan.*
import com.samiu.host.viewmodel.gank.GankAndroidViewModel
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
    viewModel { WanWxViewModel(get()) }
    viewModel { WanSearchViewModel(get()) }
    viewModel { GankAndroidViewModel(get()) }
}

val wanRepositoryModule = module {
    single { WanClient.service }
    single { GankClient.service }
    single { CoroutineDispatcherProvider() }
    single { WanHomeRepository() }
    single { WanSquareRepository() }
    single { WanProjectRepository() }
    single { WanSystemRepository() }
    single { WanWxRepository() }
    single { WanSearchRepository() }
    single { GankRepository() }
}

val wanModule = listOf(
    wanViewModelModule,
    wanRepositoryModule
)