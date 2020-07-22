package com.samiu.wangank.base

import com.samiu.wangank.model.http.WanClient
import com.samiu.wangank.model.repository.*
import com.samiu.wangank.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Samiu 2020/3/3
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
val viewModelModule = module {
    viewModel { WanHomeViewModel(get()) }
    viewModel { WanSquareViewModel(get()) }
    viewModel { WanProjectViewModel(get()) }
    viewModel { WanSystemViewModel(get()) }
    viewModel { WanWxViewModel(get()) }
    viewModel { WanSearchViewModel(get()) }
    viewModel { SystemDisplayViewModel(get()) }
    viewModel { WanLoginViewModel(get()) }
    viewModel { WanPersonalViewModel(get(), get()) }
}

val repositoryModule = module {
    single { WanClient.service }
    single { WanHomeRepository() }
    single { WanSquareRepository() }
    single { WanProjectRepository() }
    single { WanSystemRepository() }
    single { WanWxRepository() }
    single { WanSearchRepository() }
    single { WanLoginRepository() }
    single { WanCollectionRepository() }
}

val module = listOf(
    viewModelModule,
    repositoryModule
)