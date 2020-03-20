package com.samiu.host.base

import com.samiu.host.model.http.gank.GankClient
import com.samiu.host.model.http.wan.WanClient
import com.samiu.host.model.repository.gank.GankRepository
import com.samiu.host.model.repository.wan.*
import com.samiu.host.viewmodel.gank.GankAndroidViewModel
import com.samiu.host.viewmodel.gank.GankExpandViewModel
import com.samiu.host.viewmodel.gank.GankFrontViewModel
import com.samiu.host.viewmodel.gank.GankIosViewModel
import com.samiu.host.viewmodel.wan.*
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
    viewModel { RecyclerViewModel(get()) }
    viewModel { WanWxViewModel(get()) }
    viewModel { WanSearchViewModel(get()) }
    viewModel { GankAndroidViewModel(get()) }
    viewModel { GankIosViewModel(get()) }
    viewModel { GankFrontViewModel(get()) }
    viewModel { GankExpandViewModel(get()) }
}

val repositoryModule = module {
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

val module = listOf(
    viewModelModule,
    repositoryModule
)