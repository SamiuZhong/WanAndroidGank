package com.samiu.wangank.di

import com.samiu.wangank.data.local.WanDatabase
import com.samiu.wangank.data.local.dao.ArticleDao
import com.samiu.wangank.data.local.dao.ArticleRemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author samiu 2023/2/6
 * @email samiuzhong@outlook.com
 */
@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideArticleDao(
        database: WanDatabase
    ): ArticleDao = database.articleDao()

    @Provides
    @Singleton
    fun provideArticleRemoteKeysDao(
        database: WanDatabase
    ): ArticleRemoteKeysDao = database.articleRemoteKeysDao()
}