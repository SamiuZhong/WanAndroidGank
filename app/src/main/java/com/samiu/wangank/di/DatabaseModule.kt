package com.samiu.wangank.di

import android.content.Context
import androidx.room.Room
import com.samiu.wangank.data.local.WanDatabase
import com.samiu.wangank.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author samiu 2022/11/28
 * @email samiuzhong@outlook.com
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): WanDatabase {
        return Room.databaseBuilder(
            context,
            WanDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}