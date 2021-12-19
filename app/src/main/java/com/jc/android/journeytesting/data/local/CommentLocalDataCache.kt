package com.jc.android.journeytesting.data.local

import com.jc.android.journeytesting.domain.Comment
import javax.inject.Inject


class CommentLocalDataCache @Inject constructor(
    private val database: AppDatabase
) {
    fun getAllLocalCommentsData() = database.commentDao().getAllComments()
    suspend fun insertAllComments(comments: List<Comment>) =
        database.commentDao().insertAll(comments)
}
