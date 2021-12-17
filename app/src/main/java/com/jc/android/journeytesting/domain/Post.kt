package com.jc.android.journeytesting.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Post(
    @Json(name = "userId") val userId: String,
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String?,
    @Json(name = "body") val body: String?
)
