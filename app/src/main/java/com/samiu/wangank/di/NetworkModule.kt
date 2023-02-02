package com.samiu.wangank.di

import dagger.Module
import dagger.hilt.InstallIn

/**
 * @author samiu 2023/2/2
 * @email samiuzhong@outlook.com
 */
@Module
@InstallIn(SinceKotlin::class)
object NetworkModule {

    private const val BASE_URL = "https://www.wanandroid.com"


}