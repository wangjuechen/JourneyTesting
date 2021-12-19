package com.jc.android.journeytesting.ui.post

data class PostAndComments(
    val userId: String,
    val id: String,
    val title: String?,
    val body: String?,
    val numOfComments: Int,
)
