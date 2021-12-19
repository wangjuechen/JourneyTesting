package com.jc.android.journeytesting.data.remote

import com.jc.android.journeytesting.data.BaseDataSource
import javax.inject.Inject


class CommentRemoteDataSource @Inject constructor(
    private val apiService: JSONPlaceholderService,
) : BaseDataSource() {
    suspend fun getAllComments() = getResult { apiService.getAllComments() }
    suspend fun getCommentsByPost(postId: String) =
        getResult { apiService.getCommentsByPost(postId) }

}
