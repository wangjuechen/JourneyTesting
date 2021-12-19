package com.jc.android.journeytesting.data.remote

import com.jc.android.journeytesting.domain.Comment
import com.jc.android.journeytesting.domain.Post
import com.jc.android.journeytesting.utils.Constants.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface JSONPlaceholderService {
    @GET("/posts")
    suspend fun getAllPosts(): Response<List<Post>>

    @GET("/comments")
    suspend fun getAllComments(): Response<List<Comment>>

    @GET("/comments")
    suspend fun getCommentsByPost(@Query("postId") postId: String): Response<List<Comment>?>
}