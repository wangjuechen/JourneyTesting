package com.jc.android.journeytesting.utils.module

import android.content.Context
import com.jc.android.journeytesting.BaseApplication
import com.jc.android.journeytesting.data.PostRepository
import com.jc.android.journeytesting.data.local.AppDatabase
import com.jc.android.journeytesting.data.local.PostLocalDataCache
import com.jc.android.journeytesting.data.remote.JSONPlaceholderService
import com.jc.android.journeytesting.data.remote.PostRemoteDataSource
import com.jc.android.journeytesting.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import javax.inject.Singleton

private const val CACHE_SIZE_BYTES = 10 * 1024 * 1024L // 10 MB

@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext application: Context): BaseApplication {
        return application as BaseApplication
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        cache: Cache
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.cache(cache)

        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    internal fun provideCache(context: Context): Cache {
        val httpCacheDirectory = File(context.cacheDir.absolutePath, "HttpCache")
        return Cache(httpCacheDirectory, CACHE_SIZE_BYTES)
    }

    @Provides
    @Singleton
    fun provideContext(application: BaseApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideJSONPlaceholderService(retrofit: Retrofit): JSONPlaceholderService {
        return retrofit.create(JSONPlaceholderService::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(
        postRemoteDataSource: PostRemoteDataSource,
        postLocalDataCache: PostLocalDataCache
    ) = PostRepository(postRemoteDataSource, postLocalDataCache)
}