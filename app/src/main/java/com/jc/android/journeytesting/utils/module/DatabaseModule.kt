package com.jc.android.journeytesting.utils.module

import android.content.Context
import com.jc.android.journeytesting.data.local.AppDatabase
import com.jc.android.journeytesting.data.local.CommentLocalDataCache
import com.jc.android.journeytesting.data.local.PostLocalDataCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providePostLocalDataCache(db: AppDatabase) = PostLocalDataCache(db)

    @Singleton
    @Provides
    fun providePostDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Provides
    @Singleton
    fun provideCommentLocalDataCache(db: AppDatabase) = CommentLocalDataCache(db)

    @Singleton
    @Provides
    fun providePostDao(db: AppDatabase) = db.postDao()

    @Singleton
    @Provides
    fun provideCommentDao(db: AppDatabase) = db.commentDao()
}

