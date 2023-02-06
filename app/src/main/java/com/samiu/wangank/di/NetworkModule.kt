package com.samiu.wangank.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.samiu.wangank.BuildConfig
import com.samiu.wangank.data.remote.WanApiService
import com.samiu.wangank.utils.Constants
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

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideHttpClient(
        context: Context
    ): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        return OkHttpClient.Builder()
            .readTimeout(Constants.Network.TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(Constants.Network.TIMEOUT, TimeUnit.SECONDS)
            .cache(Cache(context.cacheDir, Constants.Network.CACHE_SIZE))
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(logger)
                }
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.Network.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): WanApiService {
        return retrofit.create(WanApiService::class.java)
    }
}