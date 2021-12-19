package com.jc.android.journeytesting.data

import com.jc.android.journeytesting.data.local.CommentLocalDataCache
import com.jc.android.journeytesting.data.remote.CommentRemoteDataSource
import com.jc.android.journeytesting.domain.Comment
import com.jc.android.journeytesting.utils.DataAccessStrategy
import javax.inject.Inject


class CommentRepository @Inject constructor(
    private val commentRemoteDataSource: CommentRemoteDataSource,
    private val commentLocalDataCache: CommentLocalDataCache
) {
    fun getAllComments() = DataAccessStrategy.performGetOperation(
        databaseQuery = { commentLocalDataCache.getAllLocalCommentsData() },
        networkCall = { commentRemoteDataSource.getAllComments() },
        saveCallResult = { commentLocalDataCache.insertAllComments(it) }
    )

    suspend fun getCommentsByPost(postId: String): List<Comment>? {
        return commentRemoteDataSource.getCommentsByPost(postId).data
    }
}

