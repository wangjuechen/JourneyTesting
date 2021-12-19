package com.jc.android.journeytesting.data.remote

import com.jc.android.journeytesting.data.BaseDataSource
import javax.inject.Inject


class PostRemoteDataSource @Inject constructor(
    private val apiService: JSONPlaceholderService,
): BaseDataSource() {
    suspend fun getAllPosts() = getResult { apiService.getAllPosts() }
}