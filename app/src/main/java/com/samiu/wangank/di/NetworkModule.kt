package com.samiu.wangank.di

import android.content.Context
import com.samiu.wangank.BuildConfig
import com.samiu.wangank.data.remote.WanApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author samiu 2023/2/2
 * @email samiuzhong@outlook.com
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://www.wanandroid.com"

    @Singleton
    @Provides
    fun provideHttpClient(
        context: Context
    ): OkHttpClient {
        val cacheSize = 25 * 1024 * 1024L // 25 MiB
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .cache(Cache(context.cacheDir, cacheSize))
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(logger)
                }
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): WanApiService {
        return retrofit.create(WanApiService::class.java)
    }
}