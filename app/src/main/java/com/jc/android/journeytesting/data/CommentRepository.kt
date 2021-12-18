package com.jc.android.journeytesting.data

import com.jc.android.journeytesting.data.remote.JSONPlaceholderService
import com.jc.android.journeytesting.domain.Comment
import javax.inject.Inject


class CommentRepository @Inject constructor(private val apiService: JSONPlaceholderService) {
    suspend fun getCommentsByPost(postId: String): List<Comment>? {
        //TODO: fetch from cached data if any
        return apiService.getCommentsByPost(postId).body()
    }
}