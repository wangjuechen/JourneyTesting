package com.jc.android.journeytesting.data.local

import com.jc.android.journeytesting.domain.Post
import javax.inject.Inject


class PostLocalDataCache @Inject constructor(
    private val database: AppDatabase
) {
    fun getAllLocalPostsData() = database.postDao().getAllPosts()
    suspend fun insertAllPosts(posts: List<Post>) = database.postDao().insertAll(posts)
}