package com.samiu.wangank.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.samiu.wangank.data.local.Converters
import com.samiu.wangank.data.local.WanDatabase
import com.samiu.wangank.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author samiu 2023/2/6
 * @email samiuzhong@outlook.com
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        converters: Converters
    ): WanDatabase {
        return Room.databaseBuilder(
            context,
            WanDatabase::class.java,
            Constants.Database.DATABASE_NAME
        ).addTypeConverter(converters).build()
    }
}