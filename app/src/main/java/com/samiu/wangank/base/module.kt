package com.samiu.wangank.base

import com.samiu.wangank.http.WanClient
import com.samiu.wangank.ui.home.WanHomeRepository
import com.samiu.wangank.ui.home.WanHomeViewModel
import com.samiu.wangank.ui.mine.WanMineRepository
import com.samiu.wangank.ui.mine.collect.WanCollectFragment
import com.samiu.wangank.ui.mine.collect.WanCollectViewModel
import com.samiu.wangank.ui.mine.login.WanLoginViewModel
import com.samiu.wangank.ui.mine.personal.WanPersonalViewModel
import com.samiu.wangank.ui.mine.share.WanShareViewModel
import com.samiu.wangank.ui.project.WanProjectRepository
import com.samiu.wangank.ui.project.WanProjectViewModel
import com.samiu.wangank.ui.search.WanSearchRepository
import com.samiu.wangank.ui.search.WanSearchViewModel
import com.samiu.wangank.ui.square.WanSquareRepository
import com.samiu.wangank.ui.square.WanSquareViewModel
import com.samiu.wangank.ui.system.SystemDisplayViewModel
import com.samiu.wangank.ui.system.WanSystemRepository
import com.samiu.wangank.ui.system.WanSystemViewModel
import com.samiu.wangank.ui.wxpub.WanWxRepository
import com.samiu.wangank.ui.wxpub.WanWxViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Samiu 2020/3/3
 * @email samiuzhong@outlook.com
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
    viewModel { WanPersonalViewModel(get()) }
    viewModel { WanShareViewModel(get()) }
    viewModel { WanCollectViewModel(get()) }
}

val repositoryModule = module {
    single { WanClient.service }
    single { WanHomeRepository() }
    single { WanSquareRepository() }
    single { WanProjectRepository() }
    single { WanSystemRepository() }
    single { WanWxRepository() }
    single { WanSearchRepository() }
    single { WanMineRepository() }
}

val module = listOf(
    viewModelModule,
    repositoryModule
)