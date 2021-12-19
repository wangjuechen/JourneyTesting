package com.jc.android.journeytesting.data

import com.jc.android.journeytesting.data.local.PostLocalDataCache
import com.jc.android.journeytesting.data.remote.PostRemoteDataSource
import com.jc.android.journeytesting.utils.DataAccessStrategy.performGetOperation
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postRemoteDataSource: PostRemoteDataSource,
    private val postLocalDataCache: PostLocalDataCache
) : BaseDataSource() {
    fun getAllPosts() = performGetOperation(
        databaseQuery = { postLocalDataCache.getAllLocalPostsData() },
        networkCall = { postRemoteDataSource.getAllPosts() },
        saveCallResult = { postLocalDataCache.insertAllPosts(it) }
    )
}

