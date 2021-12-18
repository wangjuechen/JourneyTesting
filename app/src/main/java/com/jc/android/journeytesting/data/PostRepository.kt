package com.jc.android.journeytesting.data

import com.jc.android.journeytesting.data.local.PostLocalDataCache
import com.jc.android.journeytesting.data.remote.JSONPlaceholderService
import com.jc.android.journeytesting.domain.Comment
import com.jc.android.journeytesting.domain.Post
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val apiService: JSONPlaceholderService,
    private val postLocalDataCache: PostLocalDataCache
) {
    suspend fun getAllPosts(): List<Post>? {
        //TODO: fetch cached data first if any
        return apiService.getAllPosts().body()
    }
}
